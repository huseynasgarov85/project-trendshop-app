package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.BasketRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final UsersRepository usersRepository;

    public List<BasketDto> getAllBaskets() {
        log.info("ActionLog.getAllBaskets.started");
        var basketEntity = basketRepository.findAll();
        var basketDto = basketEntity.stream().map(basketMapper::mapToDto).toList();
        log.info("ActionLog.getAllBaskets.end");
        return basketDto;
    }

    public BasketDto getById(Long basketId) {
        log.info("ActionLog.getById.started:basketId {}", basketId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> {
            log.error("Action is failed basketId : {}", basketId);
            return new NotFoundException("basketId not found");
        });
        var basketDto = basketMapper.mapToDto(basketEntity);
        log.info("ActionLog.getById.started:basketId {}", basketId);
        return basketDto;
    }

    public void addBasket(BasketDto basketDto) {
        log.info("ActionLog.addBasket.started: basketDto {}", basketDto);
        var basketEntity = basketMapper.mapToEntity(basketDto);
        basketRepository.save(basketEntity);
        log.info("ActionLog.addBasket.end: basketDto {}", basketDto);
    }

    public void updateBasket(Long basketId, Long userId, BasketDto basketDto) {
        log.info("ActionLog.updateBasket.started: basketId {},userId {}", basketId, userId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> {
            log.error("Action is failed basketId : {}", basketId);
            return new NotFoundException("basketId not found");
        });
        if (!basketEntity.getUser().getId().equals(userId)) {
            log.error("Action is failed userId");
            throw new NotFoundException("user not found");
        }
        var updateBasket = basketMapper.mapToEntity(basketEntity, basketDto);
        basketRepository.save(updateBasket);
        log.info("ActionLog.updateBasket.started: basketId {},userId {}", basketId, userId);
    }

    public void removeBasket(Long basketId) {
        log.info("ActionLog.removeBasket.started: basketId {}", basketId);
        basketRepository.deleteById(basketId);
        log.info("ActionLog.removeBasket.end: basketId {}", basketId);
    }

}
