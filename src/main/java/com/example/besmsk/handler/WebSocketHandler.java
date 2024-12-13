package com.example.besmsk.handler;


import com.example.besmsk.model.*;
import com.example.besmsk.service.*;
import com.example.besmsk.util.WebSocketSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.List;

public class WebSocketHandler extends TextWebSocketHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final RelayService relayService;  // Khai báo RelayService
    private final ScheduleService scheduleService;  // Khai báo RelayService
    private final DeviceService deviceService;
    private final LogService logService;
    private final NotificationService notificationService;

    // Constructor tiêm RelayService
    public WebSocketHandler(RelayService relayService, ScheduleService scheduleService, DeviceService deviceService, LogService logService, NotificationService notificationService) {
        this.relayService = relayService;
        this.scheduleService = scheduleService;
        this.deviceService = deviceService;
        this.logService = logService;
        this.notificationService = notificationService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        try {
            // Parse JSON payload
            JSONObject jsonObject = new JSONObject(payload);
            String event = jsonObject.getString("event");
            System.out.println("ASDWa");
            // Xử lý sự kiện "register"
            if ("register".equals(event)) {
                String productId = jsonObject.getString("productId");

                String eventsString = jsonObject.getJSONArray("events").toString();
                ObjectMapper objectMapper = new ObjectMapper();

                List<String> events = objectMapper.readValue(eventsString, List.class);
                if (productId != null) {
                    WebSocketSessionManager.register(productId, session);
                    WebSocketSessionManager.registerSession(session, events);
                    System.out.println("Device registered with productId: " + productId);

                    session.sendMessage(new TextMessage("Registered with productId: " + productId));
                } else {
                    session.sendMessage(new TextMessage("Error: productId is required"));
                }
            } else if ("request".equals(event)) {

                String productId = jsonObject.getString("productId");
                String mess = jsonObject.getJSONObject("data").toString();
                JSONObject content = jsonObject.getJSONObject("data");

                // Gửi dữ liệu đến tất cả các client đã đăng ký nghe theo productId
                WebSocketSessionManager.sendMessageToProduct(productId, content.getString("event"), mess, session);
            } else if ("notify".equals(event)) {


                String productId = jsonObject.getString("productId");
                JSONObject content = jsonObject.getJSONObject("data");
                if ("notifyRelay".equals(content.getString("event"))) {
                    Device device = deviceService.getDeviceByProductId(productId);
                    Relay relay = relayService.getRelayByDeviceIdAndRelayNumber(device.getId(), content.getInt("relayNumber"));
                    content.put("id", relay.getId());
                    logService.createLog(new Log(productId, device.getId(), "Device " + productId + " " + (content.getInt("status") == 1 ? "On" : "OFF") + " Relay " + (content.getInt("relayNumber")), new Date()));
                    relayService.updateStatusRelayById(productId, content.getInt("relayNumber"), content.getInt("status") == 1);
                }
                if ("notifyCreateSchedule".equals(content.getString("event"))) {
//                Schedule schedule = new Schedule(productId,content.getString("time"),content.getString("code"), new Date());
//                scheduleService.createSchedule(schedule);
                }
                if ("notifyUpdateSchedule".equals(content.getString("event"))) {

                    Schedule schedule = scheduleService.getScheduleById(content.getString("id"));

                    if (schedule != null) {

                        StringBuilder sb = new StringBuilder(schedule.getCode());
                        Notification notification = new Notification(productId, "0", "Schedule " +schedule.getTime() + " " + (sb.charAt(1) == '1' ? "ON" : "OFF") + " successfully", new Date());
                        notificationService.saveNotification(notification);
                        WebSocketSessionManager.sendMessageToProduct(productId, "notification",
                                "{\"event\":\"notification\",\"productId\":\"" + productId + "\",\"id\":\"" + notification.getId() + "\",\"type\":\"" + notification.getType() + "\",\"content\":\"" + notification.getContent() + "\",\"createAt\":\"" + notification.getCreateAt() + "\"}", null);

                        sb.setCharAt(0, '0');  // Thay đổi ký tự đầu tiên thành 'H'
                        schedule.setCode(sb.toString());
                        scheduleService.updateScheduleById(content.getString("id"), schedule);
                    }

                }
                // Gửi dữ liệu đến tất cả các client đã đăng ký nghe theo productId
                WebSocketSessionManager.sendMessageToProduct(productId, content.getString("event"), content.toString(), session);
            } else {
                session.sendMessage(new TextMessage("Unrecognized event: " + event));
            }
        } catch (JSONException e) {
            // Xử lý lỗi nếu payload không phải là JSON hợp lệ
            System.err.println("Error parsing payload to JSONObject: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Disconnected: " + session.getId());

        // Hủy đăng ký khi ngắt kết nối
        WebSocketSessionManager.unregister(session);
    }
}