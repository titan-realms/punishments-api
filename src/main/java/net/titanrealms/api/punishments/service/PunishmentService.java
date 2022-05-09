package net.titanrealms.api.punishments.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.model.ReversalInfo;
import net.titanrealms.api.punishments.repository.PunishmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PunishmentService {
    private final PunishmentRepository punishmentRepository;
    private final MongoTemplate mongoTemplate;
    private final Jedis jedis;

    private final ObjectMapper objectMapper;

    public Punishment addPunishment(Punishment punishment) throws JsonProcessingException {
        Punishment created = this.punishmentRepository.save(punishment);
        this.jedis.publish("punishment_api_punishments", this.objectMapper.writeValueAsString(created));
        return created;
    }

    public Punishment reversePunishment(String punishmentId, ReversalInfo reversalInfo) throws JsonProcessingException {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(punishmentId));

        Update update = new Update();
        update.set("reversalInfo", reversalInfo);

        Punishment modified = this.mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Punishment.class);
        this.jedis.publish("punishment_api_reversals", this.objectMapper.writeValueAsString(modified));

        return modified;
    }

    public Page<Punishment> getPunishments(Pageable pageable, UUID target) {
        return this.punishmentRepository.findAllByTarget(target, pageable);
    }

    public List<Punishment> getNonNotifiedPunishments(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("notified").is(false))
                .addCriteria(Criteria.where("target").is(target))
                .addCriteria(Criteria.where("reversalInfo").exists(false))
                .with(Sort.by(Sort.Direction.DESC, "_id"));

        return this.mongoTemplate.find(query, Punishment.class);
    }

    public Punishment getActiveBan(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("target").is(target))
                .addCriteria(Criteria.where("expiry").gte(Instant.now()));

        return this.mongoTemplate.find(query, Punishment.class).stream().findFirst().orElse(null);
    }
}
