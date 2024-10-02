package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.ShoesEntity
import com.example.projecttrendshopapp.dao.repository.ShoesRepository
import com.example.projecttrendshopapp.mapper.ShoesMapper
import com.example.projecttrendshopapp.dto.ShoesDto
import com.example.projecttrendshopapp.service.serviceImpl.shoes.ShoesImplService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ShoesServiceTest extends Specification {
    private ShoesRepository shoesRepository
    private ShoesMapper shoesMapper
    private ShoesImplService shoesService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        shoesRepository = Mock()
        shoesMapper = Mock()
        shoesService = new ShoesImplService(shoesRepository, shoesMapper)
    }

//    def "GetAll"() {
//        given:
//        def shoesEntity1 = random.nextObject(ShoesEntity)
//        def shoesEntity2 = random.nextObject(ShoesEntity)
//        def shoesEntityList = [shoesEntity1, shoesEntity2]
//        def shoesDto1 = random.nextObject(ShoesDto)
//        def shoesDto2 = random.nextObject(ShoesDto)
//        def shoesDtoList = [shoesDto1, shoesDto2]
//        when:
//        def result = shoesService.getAll()
//        then:
//        1 * shoesRepository.findAll() >> shoesEntityList
//        1 * shoesMapper.mapToDto(shoesEntity1) >> shoesDto1
//        1 * shoesMapper.mapToDto(shoesEntity2) >> shoesDto2
//        result == shoesDtoList
//    }

    def "GetById Successfully"() {
        given:
        Long shoeId = 1L
        def shoesEntity = random.nextObject(ShoesEntity)
        def shoesDto = random.nextObject(ShoesDto)
        when:
        shoesService.getById(shoeId)
        then:
        1 * shoesRepository.findById(shoeId) >> Optional.of(shoesEntity)
        1 * shoesMapper.mapToDto(shoesEntity) >> shoesDto
    }

    def "GetById UnSuccessfully"() {
        given:
        Long shoeId = 1L
        def shoesEntity = random.nextObject(ShoesEntity)
        def shoesDto = random.nextObject(ShoesDto)
        when:
        shoesService.getById(shoeId)
        then:
        1 * shoesRepository.findById(shoeId) >> Optional.empty()
        0 * shoesMapper.mapToDto(_)
        def exception = thrown(RuntimeException)
        exception.message == "shoes id is not find"
    }

    def Add() {
        given:
        def shoesDto = random.nextObject(ShoesDto)
        def shoesEntity = random.nextObject(ShoesEntity)
        when:
        shoesService.add(shoesDto)
        then:
        1 * shoesMapper.mapToEntity(shoesDto) >> shoesEntity
        1 * shoesRepository.save(shoesEntity)
    }

    def "UpdateShoe Successfully"() {
        given:
        def shoesDto = random.nextObject(ShoesDto)
        Long shoeId = 1L
        def shoesEntity = random.nextObject(ShoesEntity)
        when:
        shoesService.update(shoesDto, shoeId)
        then:
        1 * shoesRepository.findById(shoeId) >> Optional.of(shoesEntity)
        1 * shoesMapper.mapToEntity(shoesEntity, shoesDto)
        1 * shoesRepository.save(shoesEntity)
    }

    def "UpdateShoe UnSuccessfully"() {
        given:
        def shoesDto = random.nextObject(ShoesDto)
        Long shoeId = 1L
        when:
        shoesService.update(shoesDto, shoeId)
        then:
        1 * shoesRepository.findById(shoeId) >> Optional.empty()
        0 * shoesMapper.mapToEntity(_, _)
        0 * shoesRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "shoes id is not find"
    }

    def Remove() {
        given:
        Long shoeId = 1L
        when:
        shoesService.remove(shoeId)
        then:
        1 * shoesRepository.deleteById(shoeId)
    }
}
