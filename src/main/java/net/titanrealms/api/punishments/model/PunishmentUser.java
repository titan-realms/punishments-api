package net.titanrealms.api.punishments.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
public class PunishmentUser {

    @Id
    private UUID id;

    @DBRef
    private List<Punishment> punishments;
}
