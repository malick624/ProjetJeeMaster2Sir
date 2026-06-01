package com.master2sir.entretien.infrastructure.adapter.output.ai;

import com.master2sir.entretien.domain.port.out.PortSyntheseVocale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WhisperAdapter implements PortSyntheseVocale {

    private final WebClient webClient;
    
    @Value("${whisper.api.url}")
    private String whisperApiUrl;

    @Value("${whisper.api.key}")
    private String whisperApiKey;

    public WhisperAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public String transcrire(byte[] donneesAudio, String langue) {
        try {
            // C'est une implémentation placeholder
            // En production, vous appelleriez l'API OpenAI Whisper
            log.info("Transcription de données audio de taille: {} octets", donneesAudio.length);
            
            // Transcription placeholder
            return "Ceci est une transcription placeholder de l'API Whisper";
            
            /* L'implémentation de production serait:
            return webClient.post()
                    .uri(whisperApiUrl + "/v1/audio/transcriptions")
                    .header("Authorization", "Bearer " + whisperApiKey)
                    .contentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(buildMultipartBody(donneesAudio, langue))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            */
        } catch (Exception e) {
            log.error("Erreur lors de la transcription audio", e);
            throw new RuntimeException("Échec de la transcription audio", e);
        }
    }
}
