package com.example.HardwarePrimjer.repository;

import com.example.HardwarePrimjer.domain.Hardware;
import com.example.HardwarePrimjer.domain.SearchHardware;

import java.util.List;

public interface HardwareRepository {
    List<Hardware> getAllHardware();
    List<Hardware> getHardwareByName(String hardwareName);
    void saveNewHardware(Hardware hardware);
    List<Hardware> filterByParameters(SearchHardware searchHardware);
}
