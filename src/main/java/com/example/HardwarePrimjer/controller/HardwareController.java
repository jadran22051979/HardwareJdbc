package com.example.HardwarePrimjer.controller;

import com.example.HardwarePrimjer.dto.HardwareDTO;
import com.example.HardwarePrimjer.service.HardwareService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web-shop")
@AllArgsConstructor
public class HardwareController {
    private HardwareService hardwareService;

    @GetMapping
    public List<HardwareDTO> getAllArticles() {
        return hardwareService.getAllHardware().stream().toList();
    }

    @GetMapping("/{articleName}")
    public List<HardwareDTO> filterArticlesByName(@PathVariable String hardwareName) {
        return hardwareService.getHardwareByName(hardwareName).stream().toList();
    }
}
