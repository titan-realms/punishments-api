package net.titanrealms.api.punishments.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.titanrealms.api.punishments.model.Punishment;
import net.titanrealms.api.punishments.model.ReversalInfo;
import net.titanrealms.api.punishments.service.PunishmentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/punishment")
@RequiredArgsConstructor
public class PunishmentController {
    private final PunishmentService punishmentService;

    @PostMapping
    public Punishment addPunishment(@RequestBody Punishment punishment) throws JsonProcessingException {
        return this.punishmentService.addPunishment(punishment);
    }

    @PutMapping("/{punishmentId}/reverse")
    public Punishment reversePunishment(@PathVariable String punishmentId, @RequestBody ReversalInfo reversalInfo) throws JsonProcessingException {
        return this.punishmentService.reversePunishment(punishmentId, reversalInfo);
    }
}
