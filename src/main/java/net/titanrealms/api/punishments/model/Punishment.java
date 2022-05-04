package net.titanrealms.api.punishments.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.UUID;

@Document
@Data
public class Punishment {

    @Id
    private String id;

    private Instant timestamp;

    private Instant expiry;

    @Indexed
    private UUID punisher;

    @Indexed
    private UUID target;

    private String reason;

    @Indexed
    private boolean notified;

    private @Nullable ReversalInfo reversalInfo;
}
