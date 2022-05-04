package net.titanrealms.api.punishments.repository;

import net.titanrealms.api.punishments.model.Punishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PunishmentRepository extends MongoRepository<Punishment, String> {

    Page<Punishment> findAllByTarget(UUID target, Pageable pageable);
}
