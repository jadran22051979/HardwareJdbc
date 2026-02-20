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
@RequestMapping("/hardware")
@AllArgsConstructor
public class HardwareController {
    private HardwareService hardwareService;

    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.getAllHardware().stream().toList();
    }

    @GetMapping("/{hardwareName}")
    public List<HardwareDTO> filterHardwareByName(@PathVariable String hardwareName) {
        return hardwareService.getHardwareByName(hardwareName).stream().toList();
    }
}
