package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.TrousersEntity
import com.example.projecttrendshopapp.dao.repository.TrousersRepository
import com.example.projecttrendshopapp.mapper.TrousersMapper
import com.example.projecttrendshopapp.dto.TrousersDto
import com.example.projecttrendshopapp.service.serviceImpl.trousers.TrousersImplService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class TrousersServiceTest extends Specification {
    private TrousersRepository trousersRepository
    private TrousersMapper trousersMapper
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private TrousersImplService trousersService

    void setup() {
        trousersRepository = Mock()
        trousersMapper = Mock()
        trousersService = new TrousersImplService(trousersRepository, trousersMapper)
    }

    def "GetAll"() {
        given:
        def trousersEntity1 = random.nextObject(TrousersEntity)
        def trousersEntity2 = random.nextObject(TrousersEntity)
        def trousersEntityLists = [trousersEntity1, trousersEntity2]
        def trousersDto1 = random.nextObject(TrousersDto)
        def trousersDto2 = random.nextObject(TrousersDto)
        def trousersDtoLists = [trousersDto1, trousersDto2]
        when:
        def result = trousersService.getAll()
        then:
        1 * trousersRepository.findAll() >> trousersEntityLists
        1 * trousersMapper.mapToDto(trousersEntity1) >> trousersDto1
        1 * trousersMapper.mapToDto(trousersEntity2) >> trousersDto2
        result == trousersDtoLists
    }

    def "GetById successfully"() {
        given:
        Long trouserId = 1L
        def trousersEntity = random.nextObject(TrousersEntity)
        def trousersDto = random.nextObject(TrousersDto)
        when:
        trousersService.getById(trouserId)
        then:
        1 * trousersRepository.findById(trouserId) >> Optional.of(trousersEntity)
        1 * trousersMapper.mapToDto(trousersEntity) >> trousersDto

    }

    def "GetById unsuccessfully"() {
        given:
        Long trouserId = 1L
        def trousersDto = random.nextObject(TrousersDto)
        when:
        trousersService.getById(trouserId)
        then:
        1 * trousersRepository.findById(trouserId) >> Optional.empty()
        0 * trousersMapper.mapToDto(_) >> trousersDto
        def exception = thrown(RuntimeException)
        exception.message == "trouserId not found"

    }

    def Add() {
        given:
        def trousersDto = random.nextObject(TrousersDto)
        def trousersEntity = random.nextObject(TrousersEntity)
        when:
        trousersService.add(trousersDto)
        then:
        1 * trousersMapper.mapToEntity(trousersDto) >> trousersEntity
        1 * trousersRepository.save(trousersEntity)
    }

    def "UpdateTrouser successfully"() {
        given:
        def trousersDto = random.nextObject(TrousersDto)
        Long trouserId = 1L
        def trousersEntity = random.nextObject(TrousersEntity)
        when:
        trousersService.update(trousersDto, trouserId)
        then:
        1 * trousersRepository.findById(trouserId) >> Optional.of(trousersEntity)
        1 * trousersMapper.mapToEntity(trousersEntity, trousersDto)
        1 * trousersRepository.save(trousersEntity)
    }

    def "UpdateTrouser unsuccessfully"() {
        given:
        def trousersDto = random.nextObject(TrousersDto)
        Long trouserId = 1L
        def trousersEntity = random.nextObject(TrousersEntity)
        when:
        trousersService.update(trousersDto, trouserId)
        then:
        1 * trousersRepository.findById(trouserId) >> Optional.empty()
        0 * trousersMapper.mapToEntity(trousersEntity, trousersDto)
        0 * trousersRepository.save(trousersEntity)
        def exception = thrown(RuntimeException)
        exception.message == "trouserId not found"
    }

    def Delete() {
        given:
        Long trouserId = 1L
        when:
        trousersService.delete(trouserId)
        then:
        1 * trousersRepository.deleteById(trouserId)
    }
}
