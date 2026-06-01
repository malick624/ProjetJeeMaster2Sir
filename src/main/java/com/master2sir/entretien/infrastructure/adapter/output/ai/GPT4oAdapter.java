package com.master2sir.entretien.infrastructure.adapter.output.ai;

import com.master2sir.entretien.domain.model.Reponse;
import com.master2sir.entretien.domain.model.RapportEntretien;
import com.master2sir.entretien.domain.model.DescriptionPoste;
import com.master2sir.entretien.domain.model.Question;
import com.master2sir.entretien.domain.port.out.PortServiceChatIA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GPT4oAdapter implements PortServiceChatIA {

    private final ChatClient chatClient;

    @Override
    public DescriptionPoste analyserDescriptionPoste(String contenu) {
        String prompt = """
            Analysez la description de poste suivante et extrayez:
            - Titre
            - Description
            - Compétences requises (sous forme de liste)
            - Compétences préférées (sous forme de liste)
            - Niveau d'expérience
            - Responsabilités (sous forme de liste)
            
            Description de poste:
            {content}
            
            Retournez le résultat au format JSON.
            """;

        String formattedPrompt = prompt.replace("{content}", contenu);

        String response = chatClient.prompt()
                .user(formattedPrompt)
                .call()
                .content();
        
        // Analyser la réponse JSON et créer DescriptionPoste
        // Pour simplifier, nous allons créer une DescriptionPoste de base
        return DescriptionPoste.builder()
                .id(UUID.randomUUID())
                .titre("Développeur Logiciel")
                .description(contenu)
                .competencesRequises(List.of("Java", "Spring", "SQL"))
                .competencesPreferables(List.of("Kubernetes", "Docker"))
                .niveauExperience("Intermédiaire")
                .responsabilites(List.of("Développer des applications", "Écrire des tests"))
                .contenuBrut(contenu)
                .build();
    }

    @Override
    public List<Question> genererQuestions(DescriptionPoste descriptionPoste, int nombreQuestions) {
        String prompt = """
            Générez {count} questions d'entretien pour un poste de {title}.
            Compétences requises: {skills}
            Niveau d'expérience: {level}
            
            Pour chaque question, fournissez:
            - Le contenu de la question
            - Catégorie (technique, comportementale, situationnelle)
            - Compétences requises pour cette question
            
            Retournez le résultat au format JSON.
            """;

        String formattedPrompt = prompt
                .replace("{count}", String.valueOf(nombreQuestions))
                .replace("{title}", descriptionPoste.getTitre())
                .replace("{skills}", String.join(", ", descriptionPoste.getCompetencesRequises()))
                .replace("{level}", descriptionPoste.getNiveauExperience());

        String response = chatClient.prompt()
                .user(formattedPrompt)
                .call()
                .content();
        
        // Générer des questions d'exemple pour l'instant
        return List.of(
                Question.builder()
                        .id(UUID.randomUUID())
                        .content("Pouvez-vous expliquer votre expérience avec Spring Boot?")
                        .category("technique")
                        .requiredSkills(List.of("Spring", "Java"))
                        .orderIndex(0)
                        .build(),
                Question.builder()
                        .id(UUID.randomUUID())
                        .content("Décrivez un problème technique difficile que vous avez résolu.")
                        .category("comportementale")
                        .requiredSkills(List.of("Résolution de problèmes"))
                        .orderIndex(1)
                        .build()
        );
    }

    @Override
    public Reponse evaluerReponse(String question, String reponse, String contexte) {
        String prompt = """
            Évaluez la réponse suivante à la question d'entretien.
            
            Question: {question}
            Réponse: {answer}
            Contexte: {context}
            
            Fournissez:
            - Évaluation (1-2 phrases)
            - Score (0-100)
            - Feedback (feedback constructif)
            
            Retournez le résultat au format JSON.
            """;

        String formattedPrompt = prompt
                .replace("{question}", question)
                .replace("{answer}", reponse)
                .replace("{context}", contexte != null ? contexte : "Pas de contexte");

        String response = chatClient.prompt()
                .user(formattedPrompt)
                .call()
                .content();
        
        return Reponse.builder()
                .id(UUID.randomUUID())
                .contenu(reponse)
                .evaluation("Bonne réponse démontrant des connaissances")
                .score(75)
                .dateReponse(LocalDateTime.now())
                .feedback("Considérez fournir des exemples plus spécifiques")
                .build();
    }

    @Override
    public RapportEntretien genererRapport(List<Reponse> reponses, DescriptionPoste descriptionPoste) {
        String prompt = """
            Générez un rapport d'entretien basé sur les réponses du candidat.
            
            Poste: {title}
            Compétences requises: {skills}
            
            Réponses: {answers}
            
            Fournissez:
            - Résumé
            - Points forts (sous forme de liste)
            - Axes d'amélioration (sous forme de liste)
            - Score global (0-100)
            - Feedback personnalisé
            - Sujets recommandés pour discussion ultérieure
            
            Retournez le résultat au format JSON.
            """;

        String reponsesText = reponses.stream()
                .map(a -> "Q: " + a.getContenu() + " | Score: " + a.getScore())
                .collect(Collectors.joining("\n"));

        String formattedPrompt = prompt
                .replace("{title}", descriptionPoste.getTitre())
                .replace("{skills}", String.join(", ", descriptionPoste.getCompetencesRequises()))
                .replace("{answers}", reponsesText);

        String response = chatClient.prompt()
                .user(formattedPrompt)
                .call()
                .content();
        
        return RapportEntretien.builder()
                .id(UUID.randomUUID())
                .resume("Le candidat a montré de bonnes connaissances techniques")
                .pointsForts(List.of("Compétences Java solides", "Bonne résolution de problèmes"))
                .axesAmelioration(List.of("Besoin de plus d'expérience Spring", "Améliorer la communication"))
                .scoreGlobal(75)
                .feedbackPersonnalise("Concentrez-vous sur l'acquisition de plus d'expérience pratique avec Spring")
                .sujetsRecommandes(List.of("Spring Security", "Microservices"))
                .dateGeneration(LocalDateTime.now())
                .build();
    }
}
