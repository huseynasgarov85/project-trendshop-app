package com.example.projecttrendshopapp.service.serviceImpl.card;

import com.example.projecttrendshopapp.dao.repository.CardsRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.dto.CardsDto;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardImplService implements CardService {
    private final CardsRepository cardsRepository;
    private final CardMapper cardMapper;
    private final UsersRepository usersRepository;

    @Override
    public List<CardsDto> getAll() {
        log.info("ActionLog.getAll.started");
        var cardsEntity = cardsRepository.findAll();
        var cardsDto = cardsEntity.stream().map(cardMapper::mapToDto).toList();
        log.info("ActionLog.getAll.started");
        return cardsDto;
    }

    @Override
    public CardsDto getById(Long cardId) {
        log.info("ActionLog.getById.started:cardId {}", cardId);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> new NotFoundException("cardId not found"));
        var cardDto = cardMapper.mapToDto(cardEntity);
        log.info("ActionLog.getById.end:cardId {}", cardId);
        return cardDto;
    }

    @Override
    public void add(CardsDto cardsDto) {
        log.info("ActionLog.add.started:cardsDto {}", cardsDto);
        var cardsEntity = cardMapper.mapToEntity(cardsDto);
        cardsRepository.save(cardsEntity);
        log.info("ActionLog.add.end:cardsDto {}", cardsDto);
    }

    @Override
    public void update(CardsDto cardsDto, Long cardId) {
        log.info("ActionLog.update.started:cardId {},cardsDto {}", cardId, cardsDto);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> new NotFoundException("cardId not found"));
        cardMapper.mapToEntity(cardEntity, cardsDto);
        cardsRepository.save(cardEntity);
        log.info("ActionLog.update.end:cardId {},cardsDto {}", cardId, cardsDto);
    }

    @Override
    public void remove(Long cardId) {
        log.info("ActionLog.remove.started:cardId {}", cardId);
        cardsRepository.deleteById(cardId);
        log.info("ActionLog.remove.started:cardId {}", cardId);
    }

    @Override
    public void addCardsToUser(Long userId, CardsDto cardsDto) {
        log.info("ActionLog.addCardsToUser.started:userId {},cardsDto {}", userId, cardsDto);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var cardsEntity = cardMapper.mapToEntity(cardsDto);
        cardsEntity.setUsers(userEntity);
        cardsRepository.save(cardsEntity);
        log.info("ActionLog.addCardsToUser.end:userId {},cardsDto {}", userId, cardsDto);
    }

}
