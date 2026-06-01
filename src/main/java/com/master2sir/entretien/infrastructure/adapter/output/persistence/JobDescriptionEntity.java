package com.master2sir.entretien.infrastructure.adapter.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "job_descriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "title")
    private String titre;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "required_skills", columnDefinition = "TEXT")
    private String competencesRequises;
    
    @Column(name = "preferred_skills", columnDefinition = "TEXT")
    private String competencesPreferables;
    
    @Column(name = "experience_level")
    private String niveauExperience;
    
    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private String responsabilites;
    
    @Column(name = "raw_content", columnDefinition = "TEXT")
    private String contenuBrut;
}
