package net.titanrealms.api.punishments.model;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ReversalInfo {

    private UUID reverser;

    private Instant timestamp;

    private String reason;
}
