package com.example.projecttrendshopapp.service.serviceImpl.order;

import com.example.projecttrendshopapp.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderService {
    Page<OrderDto> getAll(Pageable pageable);

    OrderDto getById(Long orderId);

    void create(Long userId, Long orderId);

    void remove(Long orderId);
}
