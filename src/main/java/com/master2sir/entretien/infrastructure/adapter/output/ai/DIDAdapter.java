package com.master2sir.entretien.infrastructure.adapter.output.ai;

import com.master2sir.entretien.domain.port.out.PortGenerationAvatar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class DIDAdapter implements PortGenerationAvatar {

    private final WebClient webClient;
    
    @Value("${did.api.url}")
    private String didApiUrl;

    @Value("${did.api.key}")
    private String didApiKey;

    public DIDAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public String genererUrlVideo(String texte, String idAvatar) {
        try {
            // C'est une implémentation placeholder
            // En production, vous appelleriez l'API D-ID
            log.info("Génération de vidéo avatar pour le texte: {} avec l'avatar: {}", texte, idAvatar);
            
            // URL vidéo placeholder
            return "https://example.com/avatar-video.mp4";
            
            /* L'implémentation de production serait:
            Map<String, Object> requestBody = Map.of(
                    "script", Map.of("type", "text", "input", texte),
                    "config", Map.of("fluent", true),
                    "source_url", idAvatar
            );
            
            String response = webClient.post()
                    .uri(didApiUrl + "/talks")
                    .header("Authorization", "Bearer " + didApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // Analyser la réponse pour obtenir l'URL vidéo
            return extractVideoUrl(response);
            */
        } catch (Exception e) {
            log.error("Erreur lors de la génération de vidéo avatar", e);
            throw new RuntimeException("Échec de la génération de vidéo avatar", e);
        }
    }

    @Override
    public byte[] genererFrameVideo(String texte, String idAvatar, String emotion) {
        try {
            // C'est une implémentation placeholder
            // En production, vous appelleriez l'API D-ID pour le streaming
            log.info("Génération de frame avatar pour le texte: {} avec l'émotion: {}", texte, emotion);
            
            // Données frame placeholder
            return new byte[0];
            
            /* L'implémentation de production serait:
            return webClient.post()
                    .uri(didApiUrl + "/talks/stream")
                    .header("Authorization", "Bearer " + didApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "script", Map.of("type", "text", "input", texte),
                            "config", Map.of("fluent", true),
                            "source_url", idAvatar
                    ))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
            */
        } catch (Exception e) {
            log.error("Erreur lors de la génération de frame avatar", e);
            throw new RuntimeException("Échec de la génération de frame avatar", e);
        }
    }
}
