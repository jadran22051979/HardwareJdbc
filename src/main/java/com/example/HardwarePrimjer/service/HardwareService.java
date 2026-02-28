package com.example.HardwarePrimjer.service;

import com.example.HardwarePrimjer.dto.HardwareDTO;
import com.example.HardwarePrimjer.dto.SearchHardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> getAllHardware();

    List<HardwareDTO> getHardwareByName(String hardwareName);

//    void saveNewHardware(HardwareDTO hardware);

    List<HardwareDTO> filterByParameters(SearchHardwareDTO searchHardwareDTO);

    HardwareDTO saveNewHardwarePost(HardwareDTO hardware);

    Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id);

    boolean hardwareByIdExists(Integer id);

    boolean deleteHardwareById(Integer id);
}
