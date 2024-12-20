package com.example.besmsk.config;

import com.example.besmsk.handler.WebSocketHandler;
import com.example.besmsk.service.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final RelayService relayService;
    private final ScheduleService scheduleService;
    private final DeviceService deviceService;
    private final LogService logService;
    private final NotificationService notificationService;
    public WebSocketConfig(RelayService relayService, ScheduleService scheduleService, DeviceService deviceService,LogService logService, NotificationService notificationService) {
        this.relayService = relayService;  // Tiêm RelayService qua constructor
        this.scheduleService = scheduleService;
        this.deviceService = deviceService;
        this.logService = logService;
        this.notificationService = notificationService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(relayService,scheduleService,deviceService,logService,notificationService), "/websocket")
                .setAllowedOrigins("*"); // Cho phép tất cả các origin
    }
}
