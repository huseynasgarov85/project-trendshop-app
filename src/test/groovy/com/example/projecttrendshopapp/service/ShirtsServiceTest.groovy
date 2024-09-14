package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.ShirtEntity
import com.example.projecttrendshopapp.dao.repository.ShirtsRepository
import com.example.projecttrendshopapp.mapper.ShirtsMapper
import com.example.projecttrendshopapp.dto.ShirtDto
import com.example.projecttrendshopapp.service.serviceImpl.shirt.ShirtsImplService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ShirtsServiceTest extends Specification {
    private ShirtsRepository shirtsRepository
    private ShirtsMapper shirtsMapper
    private ShirtsImplService shirtsService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        shirtsRepository = Mock()
        shirtsMapper = Mock()
        shirtsService = new ShirtsImplService(shirtsRepository,shirtsMapper)
    }

    def "GetAll"() {
        given:
        def shirtEntity1 = random.nextObject(ShirtEntity)
        def shirtEntity2 = random.nextObject(ShirtEntity)
        def shirtEntityList = [shirtEntity1,shirtEntity2]
        def shirtsDto1 = random.nextObject(ShirtDto)
        def shirtsDto2 = random.nextObject(ShirtDto)
        def shirtDtoList = [shirtsDto1,shirtsDto2]
        when:
       def result = shirtsService.getAll()
        then:
        1 * shirtsRepository.findAll() >> shirtEntityList
        1 * shirtsMapper.mapToDto(shirtEntity1) >> shirtsDto1
        1 * shirtsMapper.mapToDto(shirtEntity2) >> shirtsDto2
        result == shirtDtoList
    }

    def "GetById Successfully"() {
        given:
        Long shirtId = 1L
        def shirtsEntity = random.nextObject(ShirtEntity)
        def shirtsDto = random.nextObject(ShirtDto)
        when:
        shirtsService.getById(shirtId)
        then:
        1 * shirtsRepository.findById(shirtId) >> Optional.of(shirtsEntity)
        1 * shirtsMapper.mapToDto(shirtsEntity) >> shirtsDto
    }


    def "GetById UnSuccessfully"() {
        given:
        Long shirtId = 1L
        when:
        shirtsService.getById(shirtId)
        then:
        1 * shirtsRepository.findById(shirtId) >> Optional.empty()
        0 * shirtsMapper.mapToDto(_)
        def exception =thrown(RuntimeException)
        exception.message == "shirt not found"
    }

    def Add() {
        given:
        def shirtsDto = random.nextObject(ShirtDto)
        def shirtsEntity = random.nextObject(ShirtEntity)
        when:
        shirtsService.add(shirtsDto)
        then:
        1 * shirtsMapper.mapToEntity(shirtsDto) >> shirtsEntity
        1 * shirtsRepository.save(shirtsEntity)
    }

    def "UpdateShirt Successfully"() {
        given:
        Long shirtId = 1L
        def shirtsDto = random.nextObject(ShirtDto)
        def shirtsEntity = random.nextObject(ShirtEntity)
        when:
        shirtsService.update(shirtsDto,shirtId)
        then:
        1 * shirtsRepository.findById(shirtId) >> Optional.of(shirtsEntity)
        1 * shirtsMapper.mapToEntity(shirtsEntity,shirtsDto) >> shirtsEntity
        1 * shirtsRepository.save(shirtsEntity)
    }

    def "UpdateShirt UnSuccessfully"() {
        given:
        Long shirtId = 1L
        def shirtsDto = random.nextObject(ShirtDto)
        when:
        shirtsService.update(shirtsDto,shirtId)
        then:
        1 * shirtsRepository.findById(shirtId) >> Optional.empty()
        0 * shirtsMapper.mapToEntity(_,_)
        0 * shirtsRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "Shirt not found"
    }

    def Remove() {
        given:
        Long shirtId = 1L
        when:
        shirtsService.remove(shirtId)
        then:
        1 * shirtsRepository.deleteById(shirtId)
    }
}
