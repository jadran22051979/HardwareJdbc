package com.example.HardwarePrimjer.service;

import com.example.HardwarePrimjer.domain.Category;
import com.example.HardwarePrimjer.domain.Hardware;
import com.example.HardwarePrimjer.domain.SearchHardware;
import com.example.HardwarePrimjer.dto.HardwareDTO;
import com.example.HardwarePrimjer.dto.SearchHardwareDTO;
import com.example.HardwarePrimjer.repository.HardwareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HardwareServiceImpl implements HardwareService {
    private HardwareRepository hardwareRepository;

    @Override
    public List<HardwareDTO> getAllHardware() {
        return hardwareRepository.getAllHardware().stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
    }

    @Override
    public List<HardwareDTO> getHardwareByName(String hardwareName) {
        return hardwareRepository.getHardwareByName(hardwareName).stream()
                .map(this::convertHardwareToHardwareDTO)
                .toList();
    }

//    @Override
//    public void saveNewHardware(HardwareDTO hardware) {
//        hardwareRepository.saveNewHardware(convertHardwareDtoToHardware(hardware));
//    }

    @Override
    public List<HardwareDTO> filterByParameters(SearchHardwareDTO searchhardwareDTO) {
        return hardwareRepository.filterByParameters(
                        convertSearchArticleDtoToSearchArticle(searchhardwareDTO))
                .stream().map(this::convertHardwareToHardwareDTO)
                .toList();
    }

    private HardwareDTO convertHardwareToHardwareDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getName(),
                hardware.getCode(), hardware.getPrice(),
                hardware.getQuantity(),
                hardware.getCategory().getName());
    }

    private Hardware convertHardwareDtoToHardware(HardwareDTO hardwareDTO) {
        Integer latestId =
                hardwareRepository.getAllHardware().stream()
                        .max((a1, a2) -> a1.getId().compareTo(a2.getId()))
                        .get().getId();

        return new Hardware(latestId + 1, hardwareDTO.getHardwareName(),
                hardwareDTO.getHardwareCode(), hardwareDTO.getHardwarePrice(),
                hardwareDTO.getQuantity(),
                Category.valueOf(hardwareDTO.getCategoryName()));
    }

    private SearchHardware convertSearchArticleDtoToSearchArticle(SearchHardwareDTO searchHardwareDTO) {
        return new SearchHardware(
                searchHardwareDTO.getHardwareName(),
                searchHardwareDTO.getHardwareCode(),
                searchHardwareDTO.getLowerPrice(),
                searchHardwareDTO.getUpperPrice(),
                Category.valueOf(searchHardwareDTO.getCategoryName()));
    }

    @Override
    public HardwareDTO saveNewHardwarePost(HardwareDTO hardware) {
        return convertHardwareToHardwareDTO(hardwareRepository.saveNewHardwarePost(convertHardwareDtoToHardware(hardware)));
    }

    @Override
    public Optional<HardwareDTO> updateHardware(HardwareDTO hardwareDTO, Integer id) {
        Optional<Hardware> updatedHardwareOptional =
                hardwareRepository.updateHardware(convertHardwareDtoToHardware(hardwareDTO), id);
        if (updatedHardwareOptional.isPresent()) {
            return Optional.of(convertHardwareToHardwareDTO(updatedHardwareOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean hardwareByIdExists(Integer id) {
        return hardwareRepository.hardwareByIdExists(id);
    }

    @Override
    public boolean deleteHardwareById(Integer id) {
        return hardwareRepository.deleteHardwareById(id);
    }
}
