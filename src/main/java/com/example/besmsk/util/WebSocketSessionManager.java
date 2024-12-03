package com.example.besmsk.util;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;

public class WebSocketSessionManager {

    // Lưu trữ các session theo productId
    private static ConcurrentHashMap<String, List<WebSocketSession>> productSessions = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<WebSocketSession, List<String>> sessionEvents = new ConcurrentHashMap<>();

    public static void registerSession(WebSocketSession session, List<String> events) {
        sessionEvents.put(session, new ArrayList<>(events));
    }

    // Cập nhật danh sách sự kiện của một session
    public static void addEventToSession(WebSocketSession session, String event) {
        sessionEvents.computeIfAbsent(session, k -> new ArrayList<>()).add(event);
    }

    // Xóa một sự kiện từ session
    public static void removeEventFromSession(WebSocketSession session, String event) {
        List<String> events = sessionEvents.get(session);
        if (events != null) {
            events.remove(event);
            if (events.isEmpty()) {
                sessionEvents.remove(session); // Xóa session nếu không còn sự kiện nào
            }
        }
    }

    // Lấy danh sách sự kiện của một session
    public static List<String> getEventsForSession(WebSocketSession session) {
        return sessionEvents.getOrDefault(session, Collections.emptyList());
    }

    // Đăng ký session theo productId
    public static void register(String productId, WebSocketSession session) {
        productSessions.computeIfAbsent(productId, k -> new ArrayList<>()).add(session);
    }

    // Hủy session khi ngắt kết nối
    public static void unregister(WebSocketSession session) {
        productSessions.forEach((productId, sessions) ->
                sessions.removeIf(s -> s.getId().equals(session.getId()))
        );
    }

    // Gửi tin nhắn tới tất cả các session theo productId
    public static void sendMessageToProduct(String productId, String event, String message, WebSocketSession senderSession) {
        List<WebSocketSession> sessions = productSessions.get(productId);
        if (sessions != null) {
            sessions.forEach(session -> {
                List<String> events = sessionEvents.get(session);
                if (events != null && events.contains(event)) {
                    if (session.isOpen() && (senderSession == null || !session.getId().equals(senderSession.getId()))) {
                        try {
                            session.sendMessage(new TextMessage(message));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

}
