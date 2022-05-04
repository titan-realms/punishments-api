package net.titanrealms.api.punishments.controller;

import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.service.PunishmentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PunishmentController {
    private final PunishmentService punishmentService;

    @PutMapping
    public void putPunishment(@RequestBody Punishment punishment) {
        this.punishmentService.putPunishment(punishment);
    }
}
