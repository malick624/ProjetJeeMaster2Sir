package com.master2sir.entretien.application.usecase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTOReponse {
    private UUID id;
    private UUID idQuestion;
    private String contenu;
    private String evaluation;
    private int score;
    private LocalDateTime dateReponse;
    private String feedback;
}