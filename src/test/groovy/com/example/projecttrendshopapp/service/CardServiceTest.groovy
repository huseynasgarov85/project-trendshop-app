package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.CardsEntity
import com.example.projecttrendshopapp.dao.entity.UsersEntity
import com.example.projecttrendshopapp.dao.repository.CardsRepository
import com.example.projecttrendshopapp.dao.repository.UsersRepository
import com.example.projecttrendshopapp.mapper.CardMapper
import com.example.projecttrendshopapp.model.dto.CardsDto
import com.example.projecttrendshopapp.service.services.CardService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardServiceTest extends Specification {
    private CardsRepository cardsRepository
    private CardMapper cardMapper
    private UsersRepository usersRepository
    private CardService cardService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        cardsRepository = Mock()
        cardMapper = Mock()
        usersRepository = Mock()
        cardService = new CardService(cardsRepository, cardMapper, usersRepository)
    }

    def "GetAll"() {
        given:
        def cardsEntity1 = random.nextObject(CardsEntity)
        def cardsEntity2 = random.nextObject(CardsEntity)
        def cardsEntityList = [cardsEntity1, cardsEntity2]
        def cardsDto1 = random.nextObject(CardsDto)
        def cardsDto2 = random.nextObject(CardsDto)
        def cardsDtoList = [cardsDto1, cardsDto2]
        when:
        def result = cardService.getAll()
        then:
        1 * cardsRepository.findAll() >> cardsEntityList
        1 * cardMapper.mapToDto(cardsEntity1) >> cardsDto1
        1 * cardMapper.mapToDto(cardsEntity2) >> cardsDto2
        result == cardsDtoList
    }

    def "GetById Successfully"() {
        given:
        Long cardId = 1L
        def cardsEntity = random.nextObject(CardsEntity)
        def cardsDto = random.nextObject(CardsDto)
        when:
        cardService.getById(cardId)
        then:
        1 * cardsRepository.findById(cardId) >> Optional.of(cardsEntity)
        1 * cardMapper.mapToDto(cardsEntity) >> cardsDto
    }

    def "GetById UnSuccessfully"() {
        given:
        Long cardId = 1L
        when:
        cardService.getById(cardId)
        then:
        1 * cardsRepository.findById(cardId) >> Optional.empty()
        0 * cardMapper.mapToDto(_)
        def exception = thrown(RuntimeException)
        exception.message == "cardId not found"
    }

    def Add() {
        given:
        def cardsDto = random.nextObject(CardsDto)
        def cardsEntity = random.nextObject(CardsEntity)
        when:
        cardService.add(cardsDto)
        then:
        1 * cardMapper.mapToEntity(cardsDto) >> cardsEntity
        1 * cardsRepository.save(cardsEntity)
    }

    def "UpdateCard Successfully"() {
        given:
        def cardsDto = random.nextObject(CardsDto)
        Long cardId = 1L
        def cardsEntity = random.nextObject(CardsEntity)
        when:
        cardService.update(cardsDto, cardId)
        then:
        1 * cardsRepository.findById(cardId) >> Optional.of(cardsEntity)
        1 * cardMapper.mapToEntity(cardsEntity,cardsDto)
        1 * cardsRepository.save(cardsEntity)
    }
    def "UpdateCard UnSuccessfully"() {
        given:
        def cardsDto = random.nextObject(CardsDto)
        Long cardId = 1L
        when:
        cardService.update(cardsDto, cardId)
        then:
        1 * cardsRepository.findById(cardId) >> Optional.empty()
        0 * cardMapper.mapToEntity(_,_)
        0 * cardsRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "cardId not found"
    }

    def Remove() {
        given:
        Long cardId = 1L
        when:
        cardService.remove(cardId)
        then:
        1 * cardsRepository.deleteById(cardId)
    }

    def "AddCardsToUser Successfully"() {
        given:
        Long userId = 1L
        def userEntity = random.nextObject(UsersEntity)
        def cardEntity = random.nextObject(CardsEntity)
        def cardsDto = random.nextObject(CardsDto)
        when:
        cardService.addCardsToUser(userId,cardsDto)
        then:
        1 * usersRepository.findById(userId) >> Optional.of(userEntity)
        1 * cardMapper.mapToEntity(cardsDto) >> cardEntity
        1 * cardsRepository.save(cardEntity)
    }
    def "AddCardsToUser UnSuccessfully"() {
        given:
        Long userId = 1L
        def cardsDto = random.nextObject(CardsDto)
        when:
        cardService.addCardsToUser(userId,cardsDto)
        then:
        1 * usersRepository.findById(userId) >> Optional.empty()
        0 * cardMapper.mapToEntity(_)
        0 * cardsRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "userId not found"
    }
}
