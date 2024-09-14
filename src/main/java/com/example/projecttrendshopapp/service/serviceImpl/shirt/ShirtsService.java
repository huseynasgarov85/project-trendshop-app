package com.example.projecttrendshopapp.service.serviceImpl.shirt;

import com.example.projecttrendshopapp.dto.ShirtDto;
import com.example.projecttrendshopapp.dto.ShirtFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShirtsService {
    Page<ShirtDto> getAll(Pageable pageable, ShirtFilterDto shirtFilterDto);

    ShirtDto getById(Long shirtId);

    void add(ShirtDto shirtDto);

    void update(ShirtDto shirtDto, Long shirtId);

    void remove(Long shirtId);
}
