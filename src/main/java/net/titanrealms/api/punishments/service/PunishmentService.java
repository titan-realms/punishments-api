package net.titanrealms.api.punishments.service;

import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.model.ReversalInfo;
import net.titanrealms.api.punishments.repository.PunishmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PunishmentService {
    private final PunishmentRepository punishmentRepository;
    private final MongoTemplate mongoTemplate;

    public Punishment addPunishment(Punishment punishment) {
        return this.punishmentRepository.save(punishment);
    }

    public Punishment reversePunishment(String punishmentId, ReversalInfo reversalInfo) {
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(punishmentId));

        Update update = new Update();
        update.set("reversalInfo", reversalInfo);

        return this.mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), Punishment.class);
    }

    public Page<Punishment> getPunishments(Pageable pageable, UUID target) {
        return this.punishmentRepository.findAllByTarget(target, pageable);
    }

    public List<Punishment> getNonNotifiedPunishments(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("notified").is(false))
                .addCriteria(Criteria.where("target").is(target))
                .addCriteria(Criteria.where("reversalInfo").exists(false));

        return this.mongoTemplate.find(query, Punishment.class);
    }

    public Punishment getActiveBan(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("target").is(target))
                .addCriteria(Criteria.where("expiry").gte(Instant.now()));

        return this.mongoTemplate.find(query, Punishment.class).stream().findFirst().orElse(null);
    }
}
