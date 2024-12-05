package com.example.besmsk.controller;


import com.example.besmsk.model.Schedule;
import com.example.besmsk.service.ScheduleService;
import com.example.besmsk.util.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Object> createSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        WebSocketSessionManager.sendMessageToProduct(schedule.getProductId(), "createSchedule",
                "{\"event\":\"createSchedule\",\"time\":\"" + schedule.getTime() + "\",\"id\":\"" + savedSchedule.getId() + "\",\"code\":\"" + schedule.getCode() + "\"}", null);
        return ResponseEntity.ok().body(Map.of("code", 200));
    }

    @GetMapping("/{productId}")
    public List<Schedule> getAllSchedulesByProductId(@PathVariable String productId) {
        return scheduleService.getSchedulesByProductId(productId);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Object> updateScheduleById(@PathVariable String scheduleId, @RequestBody Schedule schedule) {
        scheduleService.updateScheduleById(scheduleId, schedule);
        System.out.println(scheduleId);
        if (schedule.getCode().charAt(0) == '1') {
            WebSocketSessionManager.sendMessageToProduct(schedule.getProductId(), "createSchedule",
                    "{\"event\":\"createSchedule\",\"time\":\"" + schedule.getTime() + "\",\"id\":\"" + scheduleId + "\",\"code\":\"" + schedule.getCode() + "\"}", null);
        } else {
            WebSocketSessionManager.sendMessageToProduct(schedule.getProductId(), "deleteSchedule",
                    "{\"event\":\"deleteSchedule\",\"id\":\"" + scheduleId + "\"}", null);
        }

        return ResponseEntity.ok().body(Map.of("code", 200));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Object> deleteScheduleById(@PathVariable String scheduleId) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (scheduleService.deleteScheduleById(scheduleId)) {
            WebSocketSessionManager.sendMessageToProduct(schedule.getProductId(), "deleteSchedule",
                    "{\"event\":\"deleteSchedule\",\"id\":\"" + scheduleId + "\"}", null);
            return ResponseEntity.ok().body(Map.of("code", 200));
        }
        return ResponseEntity.ok().body(Map.of("code", 404));
    }
}
