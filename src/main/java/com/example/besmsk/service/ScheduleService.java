package com.example.besmsk.service;


import com.example.besmsk.model.Schedule;
import com.example.besmsk.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        schedule.setCreateAt(new Date());
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedulesByProductId(String productId) {
        return scheduleRepository.getSchedulesByProductId(productId);
    }

    public boolean updateScheduleById(String id, Schedule newSchedule) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    schedule.setCode(newSchedule.getCode());
                    schedule.setTime(newSchedule.getTime());
                    schedule.setCreateAt(new Date());
                    scheduleRepository.save(schedule);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteScheduleById(String id) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    scheduleRepository.delete(schedule);
                    return true;
                })
                .orElse(false);
    }
}