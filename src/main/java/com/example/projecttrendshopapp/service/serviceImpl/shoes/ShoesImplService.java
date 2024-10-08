package com.example.projecttrendshopapp.service.serviceImpl.shoes;

import com.example.projecttrendshopapp.dao.entity.ShoesEntity;
import com.example.projecttrendshopapp.dao.repository.ShoesRepository;
import com.example.projecttrendshopapp.dto.ShoesDto;
import com.example.projecttrendshopapp.dto.ShoesFilterDto;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ShoesMapper;
import com.example.projecttrendshopapp.service.specification.shirts.BalanceSpecification;
import com.example.projecttrendshopapp.service.specification.shirts.ColourSpecification;
import com.example.projecttrendshopapp.service.specification.shirts.CounterSpecification;
import com.example.projecttrendshopapp.service.specification.shirts.MarcaSpecification;
import com.example.projecttrendshopapp.service.specification.shoes.NameSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoesImplService implements ShoesService {
    private final ShoesRepository shoesRepository;
    private final ShoesMapper shoesMapper;

    @Override
    public Page<ShoesDto> getAll(ShoesFilterDto shoesFilterDto, Pageable pageable) {
        log.info("ActionLog.getAll.start");
        var specification = Specification.where(new MarcaSpecification(shoesFilterDto.getMarca()))
                .and(new BalanceSpecification(shoesFilterDto.getBalance()))
                .and(new ColourSpecification(shoesFilterDto.getColour()))
                .and(new CounterSpecification(shoesFilterDto.getCount()))
                .and(new com.example.projecttrendshopapp.service.specification.shoes.SizeSpecification(shoesFilterDto.getShoeSize()))
                .and(new NameSpecification(shoesFilterDto.getName()))
                .and(new com.example.projecttrendshopapp.service.specification.shoes.ProductCategory(shoesFilterDto.getProductCategoryShoes()));
        Page<ShoesEntity> shoesEntity = shoesRepository.findAll(specification, pageable);
        var shoesDto = shoesEntity.stream().map(shoesMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end");
        return new PageImpl<>(shoesDto, shoesEntity.getPageable(), shoesEntity.getTotalElements());
    }

    @Override
    public ShoesDto getById(Long shoeId) {
        log.info("ActionLog.getById.started:shoesId {}", shoeId);
        var shoesEntity = shoesRepository.findById(shoeId).orElseThrow(() -> new NotFoundException("shoes id is not find"));
        var shoesDto = shoesMapper.mapToDto(shoesEntity);
        log.info("ActionLog.getById.end:shoesId {}", shoeId);
        return shoesDto;
    }

    @Override
    public void add(ShoesDto shoesDto) {
        log.info("ActionLog.add.started:shoesDto {}", shoesDto);
        var shoesEntity = shoesMapper.mapToEntity(shoesDto);
        shoesRepository.save(shoesEntity);
        log.info("ActionLog.add.end:shoesDto {}", shoesDto);
    }

    @Override
    public void update(ShoesDto shoesDto, Long shoeId) {
        log.info("ActionLog.update.started:shoeId {},shoesDto {}", shoeId, shoesDto);
        var shoeEntity = shoesRepository.findById(shoeId).orElseThrow(() -> new NotFoundException("shoes id is not find"));
        shoesMapper.mapToEntity(shoeEntity, shoesDto);
        shoesRepository.save(shoeEntity);
        log.info("ActionLog.update.end:shoeId {},shoesDto {}", shoeId, shoesDto);
    }

    @Override
    public void remove(Long shoeId) {
        log.info("ActionLog.remove.started:shoeId {}", shoeId);
        shoesRepository.deleteById(shoeId);
        log.info("ActionLog.remove.end:shoeId {}", shoeId);
    }
}
