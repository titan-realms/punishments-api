package net.titanrealms.api.punishments.repository;

import net.titanrealms.api.punishments.model.Punishment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PunishmentRepository extends MongoRepository<Punishment, String> {
}
