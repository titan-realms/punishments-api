package net.titanrealms.api.punishments.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.UUID;

@Document
@Data
public class Punishment {

    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;

    private Instant timestamp;

    private Instant expiry;

    @Indexed
    private UUID punisher;

    @Indexed
    private UUID target;

    private String reason;

    @Indexed
    private boolean notified;
}
