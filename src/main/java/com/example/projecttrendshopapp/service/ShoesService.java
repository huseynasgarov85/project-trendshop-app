package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.ShoesRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ShoesMapper;
import com.example.projecttrendshopapp.model.dto.ShoesDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoesService {
    private final ShoesRepository shoesRepository;
    private final ShoesMapper shoesMapper;

    public List<ShoesDto> getAll() {
        log.info("ActionLog.getAll.start");
        var shoesEntity = shoesRepository.findAll();
        var shoesDto = shoesEntity.stream().map(shoesMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end");
        return shoesDto;
    }

    public ShoesDto getById(Long shoeId) {
        log.info("ActionLog.getById.started:shoesId {}", shoeId);
        var shoesEntity = shoesRepository.findById(shoeId).orElseThrow(() ->new NotFoundException("shoes id is not find"));
        var shoesDto = shoesMapper.mapToDto(shoesEntity);
        log.info("ActionLog.getById.end:shoesId {}", shoeId);
        return shoesDto;
    }

    public void addShoe(ShoesDto shoesDto) {
        log.info("ActionLog.addShoe.started:shoesDto {}", shoesDto);
        var shoesEntity = shoesMapper.mapToEntity(shoesDto);
        shoesRepository.save(shoesEntity);
        log.info("ActionLog.addShoe.end:shoesDto {}", shoesDto);
    }

    public void updateShoe(ShoesDto shoesDto, Long shoeId) {
        log.info("ActionLog.updateShoe.started:shoeId {},shoesDto {}", shoeId, shoesDto);
        var shoeEntity = shoesRepository.findById(shoeId).orElseThrow(() ->new NotFoundException("shoes id is not find"));
        shoesMapper.mapToEntity(shoeEntity, shoesDto);
        shoesRepository.save(shoeEntity);
        log.info("ActionLog.updateShoe.end:shoeId {},shoesDto {}", shoeId, shoesDto);
    }

    public void removeShoe(Long shoeId){
        log.info("ActionLog.removeShoe.started:shoeId {}",shoeId);
        shoesRepository.deleteById(shoeId);
        log.info("ActionLog.removeShoe.end:shoeId {}",shoeId);
    }
}
