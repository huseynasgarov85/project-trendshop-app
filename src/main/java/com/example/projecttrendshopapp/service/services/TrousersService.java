package com.example.projecttrendshopapp.service.services;

import com.example.projecttrendshopapp.dao.entity.TrousersEntity;
import com.example.projecttrendshopapp.dao.repository.TrousersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.TrousersMapper;
import com.example.projecttrendshopapp.model.dto.TrousersDto;
import com.example.projecttrendshopapp.model.dto.TrousersFilterDto;
import com.example.projecttrendshopapp.service.specification.trousers.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrousersService {
    private final TrousersRepository trousersRepository;
    private final TrousersMapper trousersMapper;

    public Page<TrousersDto> getAll(TrousersFilterDto trousersFilterDto, Pageable pageable) {
        log.info("ActionLog.getAll.started");
        var specification  = Specification.where(new ProductCategory(trousersFilterDto.getProductCategoryTrousers()))
                .and(new SizeSpecification(trousersFilterDto.getTrouserSize()))
                .and(new ColourSpecification(trousersFilterDto.getColour()))
                .and(new MarcaSpecification(trousersFilterDto.getMarca()))
                .and(new PriceSpecification(trousersFilterDto.getPrice()))
                .and(new CounterSpecification(trousersFilterDto.getCounter()));
        Page<TrousersEntity> trousersEntity = trousersRepository.findAll(specification,pageable);
        var trouserDto = trousersEntity.stream().map(trousersMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end");
        return new PageImpl<>(trouserDto,trousersEntity.getPageable(),trousersEntity.getTotalElements());
    }

    public TrousersDto getById(Long trouserId) {
        log.info("ActionLog.getById.started:trouserId {}", trouserId);
        var trouserEntity = trousersRepository.findById(trouserId).orElseThrow(() -> new NotFoundException("trouserId not found"));
        var trouserDto = trousersMapper.mapToDto(trouserEntity);
        log.info("ActionLog.getById.end:trouserId {}", trouserId);
        return trouserDto;
    }

    public void add(TrousersDto trousersDto) {
        log.info("ActionLog.add.started:trouserDto {}", trousersDto);
        var trouserEntity = trousersMapper.mapToEntity(trousersDto);
        trousersRepository.save(trouserEntity);
        log.info("ActionLog.add.end:trouserDto {}", trousersDto);
    }

    public void update(TrousersDto trousersDto, Long trouserId) {
        log.info("ActionLog.update.started:trouserId {},trousersDto{}", trouserId, trousersDto);
        var trouserEntity = trousersRepository.findById(trouserId).orElseThrow(() -> new NotFoundException("trouserId not found"));
        trousersMapper.mapToEntity(trouserEntity, trousersDto);
        trousersRepository.save(trouserEntity);
        log.info("ActionLog.update.end:trouserId {},trousersDto {}", trouserId, trousersDto);
    }

    public void delete(Long trouserId) {
        log.info("ActionLog.delete.started:trouserId {}", trouserId);
        trousersRepository.deleteById(trouserId);
        log.info("ActionLog.delete.end:trouserId {}", trouserId);
    }
}
