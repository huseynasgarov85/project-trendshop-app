package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.CardsRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.CardMapper;
import com.example.projecttrendshopapp.model.dto.CardsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {
    private final CardsRepository cardsRepository;
    private final CardMapper cardMapper;
    private final UsersRepository usersRepository;

    public List<CardsDto> getAll() {
        log.info("ActionLog.getAllUsers.started");
        var cardsEntity = cardsRepository.findAll();
        var cardsDto = cardsEntity.stream().map(cardMapper::mapToDto).toList();
        log.info("ActionLog.getAllUsers.started");
        return cardsDto;
    }

    public CardsDto getById(Long cardId) {
        log.info("ActionLog.getById.started:cardId {}", cardId);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> {
            log.error("Action is failed cardId:{}", cardId);
            return new NotFoundException("cardId not found");
        });
        var cardDto = cardMapper.mapToDto(cardEntity);
        log.info("ActionLog.getById.end:cardId {}", cardId);
        return cardDto;
    }

    public void addCard(CardsDto cardsDto) {
        log.info("ActionLog.addCard.started:cardsDto {}", cardsDto);
        var cardsEntity = cardMapper.mapToEntity(cardsDto);
        cardsRepository.save(cardsEntity);
        log.info("ActionLog.addCard.end:cardsDto {}", cardsDto);
    }

    public void updateCard(CardsDto cardsDto, Long cardId) {
        log.info("ActionLog.updateCard.started:cardId {},cardsDto {}", cardId, cardsDto);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> {
            log.error("Action is failed cardId:{}", cardId);
            return new NotFoundException("cardId not found");
        });
        cardMapper.mapToEntity(cardEntity, cardsDto);
        cardsRepository.save(cardEntity);
        log.info("ActionLog.updateCard.end:cardId {},cardsDto {}", cardId, cardsDto);
    }

    public void removeCard(Long cardId) {
        log.info("ActionLog.removeCard.started:cardId {}", cardId);
        cardsRepository.deleteById(cardId);
        log.info("ActionLog.removeCard.started:cardId {}", cardId);
    }

    public void addCardsToUser(Long userId, CardsDto cardsDto) {
        log.info("ActionLog.addCardsToUser.started:userId {},cardsDto {}", userId, cardsDto);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> {
            log.error("Action is failed userId:{}", userId);
            return new NotFoundException("userId not found");
        });
        var cardsEntity = cardMapper.mapToEntity(cardsDto);
        cardsEntity.setUsers(userEntity);
        cardsRepository.save(cardsEntity);
        log.info("ActionLog.addCardsToUser.end:userId {},cardsDto {}", userId, cardsDto);
    }

}
