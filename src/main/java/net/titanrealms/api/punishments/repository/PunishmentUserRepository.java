package net.titanrealms.api.punishments.repository;

import net.titanrealms.api.punishments.model.PunishmentUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PunishmentUserRepository extends MongoRepository<PunishmentUser, UUID> {
}
