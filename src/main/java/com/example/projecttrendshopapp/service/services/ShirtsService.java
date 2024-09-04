package com.example.projecttrendshopapp.service.services;

import com.example.projecttrendshopapp.dao.entity.ShirtEntity;
import com.example.projecttrendshopapp.dao.repository.ShirtsRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ShirtsMapper;
import com.example.projecttrendshopapp.model.dto.ShirtDto;
import com.example.projecttrendshopapp.model.dto.ShirtFilterDto;
import com.example.projecttrendshopapp.service.specification.shirts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShirtsService {
    private final ShirtsRepository shirtsRepository;
    private final ShirtsMapper shirtsMapper;


    public Page<ShirtDto> getAll(ShirtFilterDto shirtFilterDto, Pageable pageable) {
        log.info("ActionLog.getAll.started : pageable {}",pageable);
        var specification = Specification.where(new BalanceSpecification(shirtFilterDto.getBalance()))
                .and(new ColourSpecification(shirtFilterDto.getColour()))
                .and(new CounterSpecification(shirtFilterDto.getCounter()))
                .and(new SizeSpecification(shirtFilterDto.getShirtSize()))
                .and(new ProductCategory(shirtFilterDto.getProductCategoryShirt()))
                .and(new MarcaSpecification(shirtFilterDto.getMarca()));
        Page<ShirtEntity> shirtsEntity = shirtsRepository.findAll(specification,pageable);
        List<ShirtDto> shirtsDto = shirtsEntity.stream().map(shirtsMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end: shirtentity {}",shirtsEntity);
        return new PageImpl<>(shirtsDto,shirtsEntity.getPageable(),shirtsEntity.getTotalElements());
    }

    public ShirtDto getById(Long shirtsId) {
        log.info("ActionLog.getById.started:shirtsId {}", shirtsId);
        var shirtsEntity = shirtsRepository.findById(shirtsId).orElseThrow(() -> new NotFoundException("shirt not found"));
        var shirtsDto = shirtsMapper.mapToDto(shirtsEntity);
        log.info("ActionLog.getById.end:shirtsId {}", shirtsId);
        return shirtsDto;
    }

    public void add(ShirtDto shirtDto) {
        log.info("ActionLog.add.started:shirtsDto {}", shirtDto);
        var shirtsEntity = shirtsMapper.mapToEntity(shirtDto);
        shirtsRepository.save(shirtsEntity);
        log.info("ActionLog.add.end:shirtsDto {}", shirtDto);

    }

    public void update(ShirtDto shirtDto, Long shirtId) {
        log.info("ActionLog.update.started:shirtId {},shirtDto {}", shirtId, shirtDto);
        var shirtEntity = shirtsRepository.findById(shirtId).orElseThrow(() -> new NotFoundException("Shirt not found"));
        var updateShirt = shirtsMapper.mapToEntity(shirtEntity, shirtDto);
        shirtsRepository.save(updateShirt);
        log.info("ActionLog.update.end:shirtId {},shirtDto {}", shirtId, shirtDto);
    }

    public void remove(Long shirtId) {
        log.info("ActionLog.remove.started:shirtId {}", shirtId);
        shirtsRepository.deleteById(shirtId);
        log.info("ActionLog.remove.end:shirtId {}", shirtId);
    }

}
