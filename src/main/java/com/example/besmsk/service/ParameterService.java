package com.example.besmsk.service;

import com.example.besmsk.model.Parameter;
import com.example.besmsk.model.Used;
import com.example.besmsk.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;


    public Parameter createParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public List<Parameter> getAllParametersByProductId(String productId) {
        return parameterRepository.findParameterByProductId(productId);
    }

    public List<Parameter> getParametersBetween(String productId, Date start, Date end) {
        // Lấy thời gian của ngày hôm nay
        Date startOfToday = getStartOfDay(start);
        Date endOfToday = getEndOfDay(end);

        // Truy vấn dữ liệu trong khoảng thời gian ngày hôm nay
        return parameterRepository.findAllByProductIdAndCreatedAtBetween(productId,startOfToday, endOfToday);
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
}
