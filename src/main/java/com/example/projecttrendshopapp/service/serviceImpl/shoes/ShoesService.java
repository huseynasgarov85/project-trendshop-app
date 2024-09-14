package com.example.projecttrendshopapp.service.serviceImpl.shoes;

import com.example.projecttrendshopapp.dto.ShoesDto;
import com.example.projecttrendshopapp.dto.ShoesFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoesService {
    Page<ShoesDto> getAll(ShoesFilterDto shoesFilterDto, Pageable pageable);

    ShoesDto getById(Long shoeId);

    void add(ShoesDto shoesDto);

    void update(ShoesDto shoesDto, Long shoeId);

    void remove(Long shoeId);
}
