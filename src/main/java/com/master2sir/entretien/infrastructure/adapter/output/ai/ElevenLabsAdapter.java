package com.master2sir.entretien.infrastructure.adapter.output.ai;

import com.master2sir.entretien.domain.port.out.PortSyntheseParole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class ElevenLabsAdapter implements PortSyntheseParole {

    private final WebClient webClient;
    
    @Value("${elevenlabs.api.url}")
    private String elevenLabsApiUrl;

    @Value("${elevenlabs.api.key}")
    private String elevenLabsApiKey;

    public ElevenLabsAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public byte[] synthetiser(String texte, String idVoix) {
        try {
            // C'est une implémentation placeholder
            // En production, vous appelleriez l'API ElevenLabs
            log.info("Synthèse du texte: {} avec la voix: {}", texte, idVoix);
            
            // Données audio placeholder
            return new byte[0];
            
            /* L'implémentation de production serait:
            return webClient.post()
                    .uri(elevenLabsApiUrl + "/v1/text-to-speech/" + idVoix)
                    .header("Authorization", "Bearer " + elevenLabsApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of("text", texte, "model_id", "eleven_multilingual_v2"))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
            */
        } catch (Exception e) {
            log.error("Erreur lors de la synthèse vocale", e);
            throw new RuntimeException("Échec de la synthèse vocale", e);
        }
    }
}
