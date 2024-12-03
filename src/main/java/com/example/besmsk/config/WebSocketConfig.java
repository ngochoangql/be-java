package com.example.besmsk.config;

import com.example.besmsk.handler.WebSocketHandler;
import com.example.besmsk.service.RelayService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final RelayService relayService;

    public WebSocketConfig(RelayService relayService) {
        this.relayService = relayService;  // Tiêm RelayService qua constructor
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(relayService), "/websocket")
                .setAllowedOrigins("*"); // Cho phép tất cả các origin
    }
}
