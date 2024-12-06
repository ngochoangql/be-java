package com.example.besmsk.service;

import com.example.besmsk.model.Used;
import com.example.besmsk.repository.UsedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UsedService {

    @Autowired
    private UsedRepository usedRepository;

    public Used createUsed(Used used) {
        return usedRepository.save(used);
    }

    public List<Used> getUsedsByProduct(String productId) {
        return usedRepository.findUsedsByProductId(productId);
    }
//
//    public  Used getUsedByProductIdAndDate(String productId, Date date) {
//        return usedRepository.findUsedByProductIdAndCreatedAt(productId,date);
//    }

    public Used getUsedDataForToday(String productId,Date today) {
        // Lấy thời gian của ngày hôm nay
        Date startOfToday = getStartOfDay(today);
        Date endOfToday = getEndOfDay(today);

        // Truy vấn dữ liệu trong khoảng thời gian ngày hôm nay
        return usedRepository.findByProductIdAndCreatedAtBetween(productId,startOfToday, endOfToday);
    }

    public Date getStartOfDay(Date date) {
        // Chuyển đổi từ Date sang LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Lấy thời gian bắt đầu của ngày (00:00:00)
        LocalDateTime startOfDay = localDateTime.toLocalDate().atStartOfDay();
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndOfDay(Date date) {
        // Chuyển đổi từ Date sang LocalDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Lấy thời gian kết thúc của ngày (23:59:59)
        LocalDateTime endOfDay = localDateTime.toLocalDate().atTime(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    public void updateUsed(Used used) {
        usedRepository.save(used);
    }
}
