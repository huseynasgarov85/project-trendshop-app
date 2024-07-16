package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.TrousersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.TrousersMapper;
import com.example.projecttrendshopapp.model.dto.TrousersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrousersService {
    private final TrousersRepository trousersRepository;
    private final TrousersMapper trousersMapper;

    public List<TrousersDto> getAll() {
        log.info("ActionLog.getAll.started");
        var trousersEntity = trousersRepository.findAll();
        var trouserDto = trousersEntity.stream().map(trousersMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end");
        return trouserDto;
    }

    public TrousersDto getById(Long trouserId) {
        log.info("ActionLog.getById.started:trouserId {}", trouserId);
        var trouserEntity = trousersRepository.findById(trouserId).orElseThrow(() ->new NotFoundException("trouserId not found"));
        var trouserDto = trousersMapper.mapToDto(trouserEntity);
        log.info("ActionLog.getById.end:trouserId {}", trouserId);
        return trouserDto;
    }

    public void addTrouser(TrousersDto trousersDto) {
        log.info("ActionLog.addTrouser.started:trouserDto {}", trousersDto);
        var trouserEntity = trousersMapper.mapToEntity(trousersDto);
        trousersRepository.save(trouserEntity);
        log.info("ActionLog.addTrouser.end:trouserDto {}", trousersDto);
    }

    public void updateTrouser(TrousersDto trousersDto,Long trouserId){
        log.info("ActionLog.updateTrouser.started:trouserId {},trousersDto{}",trouserId,trousersDto);
        var trouserEntity = trousersRepository.findById(trouserId).orElseThrow(() ->new NotFoundException("trouserId not found"));
        trousersMapper.mapToEntity(trouserEntity,trousersDto);
        trousersRepository.save(trouserEntity);
        log.info("ActionLog.updateTrouser.end:trouserId {},trousersDto {}",trouserId,trousersDto);
    }

    public void deleteTrouser(Long trouserId){
        log.info("ActionLog.deleteTrouser.started:trouserId {}",trouserId);
        trousersRepository.deleteById(trouserId);
        log.info("ActionLog.deleteTrouser.end:trouserId {}",trouserId);
    }
}
