package com.example.projecttrendshopapp.service.serviceImpl.basket;

import com.example.projecttrendshopapp.dto.BasketDto;
import com.example.projecttrendshopapp.dto.BasketWithProductsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BasketService {
    Page<BasketDto> getAll(Pageable pageable);

    BasketWithProductsDto getById(Long basketId);

    void add(BasketDto basketDto);

    void update(Long basketId, Long userId, BasketDto basketDto);

    void remove(Long basketId);
}
