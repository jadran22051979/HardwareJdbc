package com.example.HardwarePrimjer.service;

import com.example.HardwarePrimjer.dto.HardwareDTO;
import com.example.HardwarePrimjer.dto.SearchHardwareDTO;

import java.util.List;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();
    List<HardwareDTO> getHardwareByName(String hardwareName);
    void saveNewHardware(HardwareDTO hardware);
    List<HardwareDTO> filterByParameters(SearchHardwareDTO searchHardwareDTO);
}
