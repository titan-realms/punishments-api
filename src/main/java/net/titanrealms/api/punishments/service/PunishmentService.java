package net.titanrealms.api.punishments.service;

import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.repository.PunishmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PunishmentService {
    private final PunishmentRepository punishmentRepository;
    private final MongoTemplate mongoTemplate;

    public void putPunishment(Punishment punishment) {
        this.punishmentRepository.save(punishment);
    }

    public Page<Punishment> getPunishments(Pageable pageable, UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("target").is(target))
                .with(pageable);

        List<Punishment> result = this.mongoTemplate.find(query, Punishment.class);
        long count = this.mongoTemplate.count(query, Punishment.class); // i dont like running this twice either but idk

        return new PageImpl<>(result, pageable, count);
    }

    public List<Punishment> getNonNotifiedPunishments(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("notified").is(false))
                .addCriteria(Criteria.where("target").is(target));

        return this.mongoTemplate.find(query, Punishment.class);
    }

    public Punishment getActiveBan(UUID target) {
        Query query = new Query()
                .addCriteria(Criteria.where("target").is(target))
                .addCriteria(Criteria.where("expiry").gte(Instant.now()));

        return this.mongoTemplate.find(query, Punishment.class).stream().findFirst().orElse(null);
    }
}
