package com.example.besmsk.controller;


import com.example.besmsk.model.Schedule;
import com.example.besmsk.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Object> createSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        System.out.println(savedSchedule.getCreateAt().toString());
        return ResponseEntity.ok().body(Map.of("code", 200));
    }

    @GetMapping("/{productId}")
    public List<Schedule> getAllSchedulesByProductId(@PathVariable String productId) {
        return scheduleService.getSchedulesByProductId(productId);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<Object> updateScheduleById(@PathVariable String scheduleId, @RequestBody Schedule schedule) {
        scheduleService.updateScheduleById(scheduleId,schedule);
        System.out.println(scheduleId);
        return ResponseEntity.ok().body(Map.of("code", 200));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Object> deleteScheduleById(@PathVariable String scheduleId) {
        if (scheduleService.deleteScheduleById(scheduleId)) {
            return ResponseEntity.ok().body(Map.of("code", 200));
        }
        return ResponseEntity.ok().body(Map.of("code", 404));
    }
}
