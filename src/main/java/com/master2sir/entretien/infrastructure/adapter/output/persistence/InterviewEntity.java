package com.master2sir.entretien.infrastructure.adapter.output.persistence;

import com.master2sir.entretien.domain.model.ModeInteraction;
import com.master2sir.entretien.domain.model.EtatEntretien;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interview_sessions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "candidate_id")
    private UUID idCandidat;
    
    @Column(name = "job_description_id")
    private UUID idDescriptionPoste;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interaction_mode")
    private ModeInteraction modeInteraction;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EtatEntretien etat;
    
    @Column(name = "questions_json", columnDefinition = "TEXT")
    private String questionsJson;
    
    @Column(name = "answers_json", columnDefinition = "TEXT")
    private String reponsesJson;
    
    @Column(name = "report_json", columnDefinition = "TEXT")
    private String rapportJson;
    
    @Column(name = "created_at")
    private LocalDateTime dateCreation;
    
    @Column(name = "started_at")
    private LocalDateTime dateDebut;
    
    @Column(name = "completed_at")
    private LocalDateTime dateFin;
    
    @Column(name = "duration_minutes")
    private int dureeMinutes;
    
    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
    }
}
