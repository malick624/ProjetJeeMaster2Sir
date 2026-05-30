package com.master2sir.entretien.infrastructure.adapter.input.rest;

import com.master2sir.entretien.application.service.ServiceApplicationRapport;
import com.master2sir.entretien.application.usecase.DTORapport;
import com.master2sir.entretien.domain.model.RapportEntretien;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rapports")
@RequiredArgsConstructor
public class ControleurRapport {

    private final ServiceApplicationRapport serviceApplicationRapport;

    @GetMapping("/entretiens/{interviewId}")
    public ResponseEntity<DTORapport> obtenirRapport(@PathVariable UUID interviewId) {
        RapportEntretien rapport = serviceApplicationRapport.genererRapport(interviewId);
        return ResponseEntity.ok(serviceApplicationRapport.toDTO(rapport));
    }
}
