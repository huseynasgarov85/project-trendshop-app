package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.ShirtsRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ShirtsMapper;
import com.example.projecttrendshopapp.model.dto.ShirtDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShirtsService {
    private final ShirtsRepository shirtsRepository;
    private final ShirtsMapper shirtsMapper;


    public List<ShirtDto> getAll() {
        log.info("ActionLog.getAll.started");
        var shirtsEntity = shirtsRepository.findAll();
        var shirtsDto = shirtsEntity.stream().map(shirtsMapper::mapToDto).toList();
        log.info("ActionLog.getAll.started");
        return shirtsDto;
    }

    public ShirtDto getById(Long shirtsId) {
        log.info("ActionLog.getById.started:shirtsId {}", shirtsId);
        var shirtsEntity = shirtsRepository.findById(shirtsId).orElseThrow(() -> new NotFoundException("shirt not found"));
        var shirtsDto = shirtsMapper.mapToDto(shirtsEntity);
        log.info("ActionLog.getById.end:shirtsId {}", shirtsId);
        return shirtsDto;
    }

    public void addShirt(ShirtDto shirtDto) {
        log.info("ActionLog.addShirts.started:shirtsDto {}", shirtDto);
        var shirtsEntitiy = shirtsMapper.mapToEntity(shirtDto);
        shirtsRepository.save(shirtsEntitiy);
        log.info("ActionLog.addShirts.end:shirtsDto {}", shirtDto);

    }

    public void updateShirt(ShirtDto shirtDto, Long shirtId) {
        log.info("ActionLog.updateShirts.started:shirtId {},shirtDto {}", shirtId, shirtDto);
        var shirtEntity = shirtsRepository.findById(shirtId).orElseThrow(() -> new NotFoundException("Shirt not found"));
        var updateShirt = shirtsMapper.mapToEntity(shirtEntity, shirtDto);
        shirtsRepository.save(updateShirt);
        log.info("ActionLog.updateShirts.end:shirtId {},shirtDto {}", shirtId, shirtDto);
    }

    public void removeShirt(Long shirtId) {
        log.info("ActionLog.removeShirt.started:shirtId {}", shirtId);
        shirtsRepository.deleteById(shirtId);
        log.info("ActionLog.removeShirt.end:shirtId {}", shirtId);
    }
}
