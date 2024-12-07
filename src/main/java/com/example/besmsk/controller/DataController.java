package com.example.besmsk.controller;


import com.example.besmsk.model.Device;
import com.example.besmsk.model.Parameter;
import com.example.besmsk.model.Used;
import com.example.besmsk.service.DeviceService;
import com.example.besmsk.service.ParameterService;
import com.example.besmsk.service.UsedService;
import com.example.besmsk.util.WebSocketSessionManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private UsedService usedService;
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/{productId}")
    public void data(@RequestBody List<Parameter> parameters, @PathVariable String productId) {
        System.out.println(parameters.get(0).getActivePower());

        // Cập nhật thời gian của phần tử cuối cùng trong danh sách parameters
        parameters.get(parameters.size() - 1).setCreatedAt(new Date());
        parameterService.createParameter(parameters.get(parameters.size() - 1));

        double totalKWhUsed = parameters.get(parameters.size() - 1).getActivePower();
        Date now = new Date();

        // Lấy dữ liệu used cho ngày hôm nay
        Used used = usedService.getUsedDataForToday(productId, now);
        Device device = deviceService.getDeviceByProductId(productId);

        // Nếu không có dữ liệu used, tạo mới
        if (used == null) {
            Used createdUsed = new Used(productId, device.getId(), totalKWhUsed, 1, now);
            usedService.createUsed(createdUsed);
        } else {
            // Cập nhật dữ liệu used
            used.setKwh(used.getKwh() + totalKWhUsed);
            used.setHours(used.getHours() + 1);
            usedService.updateUsed(used);
        }

        // Gửi mảng dữ liệu parameters về API store_data
        String storeDataUrl = "http://localhost:5000/api/v2/store_data/" + productId;
        restTemplate.postForObject(storeDataUrl, parameters, String.class);

        // Gửi phần tử cuối cùng về API data
        String dataUrl = "http://localhost:5000/api/v2/data/" + productId;
        Parameter lastParameter = parameters.get(parameters.size() - 1);
        String response = restTemplate.postForObject(dataUrl, lastParameter, String.class);
        try {
            // Parse JSON payload
            JSONObject jsonObject = new JSONObject(response);
            WebSocketSessionManager.sendMessageToProduct(productId, "notifyPredict",
                    "{\"event\":\"notifyPredict\",\"predict\":\"" + jsonObject.getString("predicted_device_type") + "\"}", null);
        } catch (JSONException e) {
            // Xử lý lỗi nếu payload không phải là JSON hợp lệ
            System.err.println("Error parsing payload to JSONObject: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
