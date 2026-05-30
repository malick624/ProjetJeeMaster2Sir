package com.master2sir.entretien.infrastructure.adapter.input.rest;

import com.master2sir.entretien.application.service.ServiceApplicationEntretien;
import com.master2sir.entretien.application.usecase.CommandeCreerEntretien;
import com.master2sir.entretien.application.usecase.DTOResponseEntretien;
import com.master2sir.entretien.application.usecase.CommandeDemarrerEntretien;
import com.master2sir.entretien.domain.model.ModeInteraction;
import com.master2sir.entretien.domain.model.SessionEntretien;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/entretiens")
@RequiredArgsConstructor
public class ControleurEntretien {

    private final ServiceApplicationEntretien serviceApplicationEntretien;

    @PostMapping
    public ResponseEntity<DTOResponseEntretien> creerEntretien(@RequestBody CommandeCreerEntretien commande) {
        SessionEntretien session = serviceApplicationEntretien.creerEntretien(
                commande.getIdCandidat(),
                commande.getContenuDescriptionPoste(),
                commande.getMode()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceApplicationEntretien.toDTO(session));
    }

    @PostMapping("/{id}/demarrer")
    public ResponseEntity<DTOResponseEntretien> demarrerEntretien(@PathVariable UUID id) {
        SessionEntretien session = serviceApplicationEntretien.demarrerEntretien(id);
        return ResponseEntity.ok(serviceApplicationEntretien.toDTO(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseEntretien> obtenirEntretien(@PathVariable UUID id) {
        SessionEntretien session = serviceApplicationEntretien.obtenirEntretien(id);
        return ResponseEntity.ok(serviceApplicationEntretien.toDTO(session));
    }

    @PostMapping("/{id}/terminer")
    public ResponseEntity<DTOResponseEntretien> terminerEntretien(@PathVariable UUID id) {
        SessionEntretien session = serviceApplicationEntretien.terminerEntretien(id);
        return ResponseEntity.ok(serviceApplicationEntretien.toDTO(session));
    }
}
