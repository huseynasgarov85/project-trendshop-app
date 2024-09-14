package com.example.projecttrendshopapp.service.serviceImpl.trousers;

import com.example.projecttrendshopapp.dto.TrousersDto;
import com.example.projecttrendshopapp.dto.TrousersFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrousersService {
    Page<TrousersDto> getAll(TrousersFilterDto trousersFilterDto, Pageable pageable);

    TrousersDto getById(Long trouserId);

    void add(TrousersDto trousersDto);

    void update(TrousersDto trousersDto, Long trouserId);

    void delete(Long trouserId);
}
