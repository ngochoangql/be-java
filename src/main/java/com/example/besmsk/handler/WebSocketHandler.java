package com.example.besmsk.handler;


import com.example.besmsk.util.WebSocketSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;

public class WebSocketHandler extends TextWebSocketHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // Parse JSON payload
        JSONObject jsonObject = new JSONObject(payload);
        String event = jsonObject.getString("event");

        // Xử lý sự kiện "register"
        if ("register".equals(event)) {
            String productId =  jsonObject.getString("productId");

            String eventsString = jsonObject.getString("events");
            ObjectMapper objectMapper = new ObjectMapper();

            List<String> events = objectMapper.readValue(eventsString, List.class);
            if (productId != null) {
                WebSocketSessionManager.register(productId, session);
                WebSocketSessionManager.registerSession(session,events);
                System.out.println("Device registered with productId: " + productId);

                session.sendMessage(new TextMessage("Registered with productId: " + productId));
            } else {
                session.sendMessage(new TextMessage("Error: productId is required"));
            }
        } else if ("request".equals(event)) {

            String productId = jsonObject.getString("productId");
            String mess =  jsonObject.getString("data");
            JSONObject content = jsonObject.getJSONObject("data");

            // Gửi dữ liệu đến tất cả các client đã đăng ký nghe theo productId
            WebSocketSessionManager.sendMessageToProduct(productId,content.getString("event"),mess,session);
        } else if ("notify".equals(event)) {

            String productId = jsonObject.getString("productId");
            String mess =  jsonObject.getString("data");
            JSONObject content = jsonObject.getJSONObject("data");


            // Gửi dữ liệu đến tất cả các client đã đăng ký nghe theo productId
            WebSocketSessionManager.sendMessageToProduct(productId, content.getString("event"),mess,session);
        }else {
            session.sendMessage(new TextMessage("Unrecognized event: " + event));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Disconnected: " + session.getId());

        // Hủy đăng ký khi ngắt kết nối
        WebSocketSessionManager.unregister(session);
    }
}