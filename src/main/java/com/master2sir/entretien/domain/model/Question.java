package com.master2sir.entretien.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private UUID id;
    private String content;
    private String category;
    private List<String> requiredSkills;
    private int orderIndex; 
    private String context;
    
   
}
