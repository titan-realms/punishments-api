package net.titanrealms.api.punishments.controller;

import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.service.PunishmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PunishmentController {
    private final PunishmentService punishmentService;

    @PostMapping
    public Punishment putPunishment(@RequestBody Punishment punishment) {
        return this.punishmentService.addPunishment(punishment);
    }
}
