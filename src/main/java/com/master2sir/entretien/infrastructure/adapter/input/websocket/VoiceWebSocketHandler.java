package com.master2sir.entretien.infrastructure.adapter.input.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.master2sir.entretien.application.service.ServiceModeVoix;
import com.master2sir.entretien.application.usecase.DTOQuestion;
import com.master2sir.entretien.domain.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class VoiceWebSocketHandler extends TextWebSocketHandler {

    private final ServiceModeVoix serviceModeVoix;
    private final ObjectMapper objectMapper;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        log.info("Connexion WebSocket voix établie: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        Map<String, Object> messageData = objectMapper.readValue(payload, Map.class);

        String action = (String) messageData.get("action");
        UUID idEntretien = UUID.fromString((String) messageData.get("interviewId"));

        switch (action) {
            case "obtenir_question_suivante":
                Question question = serviceModeVoix.obtenirQuestionSuivante(idEntretien);
                DTOQuestion questionDTO = toDTO(question);
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(questionDTO)));
                break;
            case "synthetiser":
                String texte = (String) messageData.get("text");
                byte[] audio = serviceModeVoix.synthetiserQuestion(texte);
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(Map.of(
                        "type", "audio",
                        "data", java.util.Base64.getEncoder().encodeToString(audio)
                ))));
                break;
            default:
                log.warn("Action inconnue: {}", action);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        log.info("Connexion WebSocket voix fermée: {}", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Erreur de transport WebSocket voix", exception);
        sessions.remove(session.getId());
    }

    private DTOQuestion toDTO(Question question) {
        return DTOQuestion.builder()
                .id(question.getId())
                .contenu(question.getContent())
                .categorie(question.getCategory())
                .competencesRequises(question.getRequiredSkills())
                .indexOrdre(question.getOrderIndex())
                .build();
    }
}
