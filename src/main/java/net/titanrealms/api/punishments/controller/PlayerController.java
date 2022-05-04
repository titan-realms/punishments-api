package net.titanrealms.api.punishments.controller;

import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.service.PunishmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player/{uuid}")
@RequiredArgsConstructor
public class PlayerController {
    private final PunishmentService punishmentService;

    @GetMapping
    public Page<Punishment> getPunishments(@PathVariable UUID uuid, @PageableDefault(sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        return this.punishmentService.getPunishments(pageable, uuid);
    }

    @GetMapping("/nonNotified")
    public List<Punishment> getNonNotifiedPunishments(@PathVariable UUID uuid) {
        return this.punishmentService.getNonNotifiedPunishments(uuid);
    }

    @GetMapping("/activeBan")
    public Punishment getActiveBan(@PathVariable UUID uuid) {
        return this.punishmentService.getActiveBan(uuid);
    }
}
