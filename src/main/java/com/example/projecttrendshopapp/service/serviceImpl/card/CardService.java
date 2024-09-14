package com.example.projecttrendshopapp.service.serviceImpl.card;

import com.example.projecttrendshopapp.dto.CardsDto;

import java.util.List;

public interface CardService {
    List<CardsDto> getAll();

    CardsDto getById(Long cardId);

    void add(CardsDto cardsDto);

    void update(CardsDto cardsDto, Long cardId);

    void remove(Long cardId);

    void addCardsToUser(Long userId, CardsDto cardsDto);
}
