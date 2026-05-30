package com.master2sir.entretien.infrastructure.config;

import com.master2sir.entretien.infrastructure.adapter.input.websocket.AvatarWebSocketHandler;
import com.master2sir.entretien.infrastructure.adapter.input.websocket.InterviewWebSocketHandler;
import com.master2sir.entretien.infrastructure.adapter.input.websocket.VoiceWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class ConfigurationWebSocket implements WebSocketConfigurer {

    private final InterviewWebSocketHandler interviewWebSocketHandler;
    private final VoiceWebSocketHandler voiceWebSocketHandler;
    private final AvatarWebSocketHandler avatarWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(interviewWebSocketHandler, "/ws/interview")
                .setAllowedOrigins("*");
        
        registry.addHandler(voiceWebSocketHandler, "/ws/voice")
                .setAllowedOrigins("*");
        
        registry.addHandler(avatarWebSocketHandler, "/ws/avatar")
                .setAllowedOrigins("*");
    }
}
