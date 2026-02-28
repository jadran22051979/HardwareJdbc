package com.example.HardwarePrimjer.repository;

import com.example.HardwarePrimjer.domain.Category;
import com.example.HardwarePrimjer.domain.Hardware;
import com.example.HardwarePrimjer.domain.SearchHardware;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MockHardwareRepository implements HardwareRepository {
    private static List<Hardware> hardwareList;

    static {
        hardwareList = new ArrayList<>();

        Hardware firstHardware = new Hardware(1, "Intel core i7", "CPU", new BigDecimal(50000), 50, Category.CPU);
        Hardware secondHardware = new Hardware(2, "GPU", "Luxury apartment", new BigDecimal(500000), 80, Category.GPU);
        Hardware thirdHardware = new Hardware(3, "MBO", "Vacation house", new BigDecimal(5000000), 90, Category.MBO);
        Hardware fourthHardware = new Hardware(4, "Mouse", "Mouse", new BigDecimal(100000), 120, Category.OTHER);

        hardwareList.add(firstHardware);
        hardwareList.add(secondHardware);
        hardwareList.add(thirdHardware);
        hardwareList.add(fourthHardware);
    }

    @Override
    public List<Hardware> getAllHardware() {
        return hardwareList;
    }

    @Override
    public List<Hardware> getHardwareByName(String hardwareName) {
        return hardwareList.stream()
                .filter(a -> a.getName().toLowerCase().contains(hardwareName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Hardware> getHardwareById(Integer id) {
        return hardwareList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public Hardware saveNewHardwarePost(Hardware hardware) {
        Integer generatedId = hardwareList.size();
        hardware.setId(hardwareList.size() + 1);
        hardwareList.add(hardware);
        return hardware;
    }

    @Override
    public Optional<Hardware> updateHardware(Hardware hardwareToUpdate, Integer id) {
        Optional<Hardware> storedHardwareOptional = hardwareList.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (storedHardwareOptional.isPresent()) {
            Hardware storedHardware = storedHardwareOptional.get();
            storedHardware.setName(hardwareToUpdate.getName());
            storedHardware.setCategory(hardwareToUpdate.getCategory());
            storedHardware.setCode(hardwareToUpdate.getCode());
            storedHardware.setPrice(hardwareToUpdate.getPrice());
            storedHardware.setQuantity(hardwareToUpdate.getQuantity());
            return Optional.of(storedHardware);
        }
        return Optional.empty();
    }


    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareList.stream().filter(a -> a.getId().equals(id)).findFirst().isPresent();
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareList.removeIf(hardware -> hardware.getId().equals(id));
    }

    @Override
    public List<Hardware> filterByParameters(SearchHardware searchHardware) {

        List<Hardware> hardware = getAllHardware();

        if (Optional.ofNullable(searchHardware.getId()).isPresent()) {
            hardware = hardware.stream()
                    .filter(a -> a.getId().equals(searchHardware.getId()))
                    .collect(Collectors.toList());
        }

        if (!Optional.of(searchHardware.getName()).isEmpty()) {
            hardware = hardware.stream()
                    .filter(a -> a.getName().contains(searchHardware.getName()))
                    .collect(Collectors.toList());
        }

        if (!Optional.of(searchHardware.getCode()).isEmpty()) {
            hardware = hardware.stream()
                    .filter(a -> a.getCode().contains(searchHardware.getCode()))
                    .collect(Collectors.toList());
        }

        if (Optional.ofNullable(searchHardware.getLowerPrice()).isPresent()) {
            hardware = hardware.stream()
                    .filter(a -> a.getPrice().compareTo(searchHardware.getLowerPrice()) >= 0)
                    .collect(Collectors.toList());
        }

        if (Optional.ofNullable(searchHardware.getUpperPrice()).isPresent()) {
            hardware = hardware.stream()
                    .filter(a -> a.getPrice().compareTo(searchHardware.getUpperPrice()) <= 0)
                    .collect(Collectors.toList());
        }

        if (Optional.ofNullable(searchHardware.getCategory()).isPresent()) {
            hardware = hardware.stream()
                    .filter(a -> a.getCategory().getId().equals(searchHardware.getCategory().getId()))
                    .collect(Collectors.toList());
        }


        return hardware;
    }
}
