package com.example.HardwarePrimjer.repository;

import com.example.HardwarePrimjer.domain.Hardware;
import com.example.HardwarePrimjer.domain.SearchHardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {
    List<Hardware> getAllHardware();

    List<Hardware> getHardwareByName(String hardwareName);

    Optional<Hardware> getHardwareById(Integer id);

//    void saveNewHardware(Hardware hardware);

    List<Hardware> filterByParameters(SearchHardware searchHardware);

    Hardware saveNewHardwarePost(Hardware hardware);

    boolean deleteHardwareById(Integer id);

    Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id);

    boolean hardwareByIdExists(Integer id);
}
