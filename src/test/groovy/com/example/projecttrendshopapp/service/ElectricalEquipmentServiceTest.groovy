package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.ElectricalEquipmentsEntity
import com.example.projecttrendshopapp.dao.repository.ElectricalEquipmentRepository
import com.example.projecttrendshopapp.mapper.ElectricalEquipmentMapper
import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto
import com.example.projecttrendshopapp.service.services.ElectricalEquipmentService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class ElectricalEquipmentServiceTest extends Specification {
    private ElectricalEquipmentRepository electricalEquipmentRepository
    private ElectricalEquipmentMapper electricalEquipmentMapper
    private ElectricalEquipmentService electricalEquipmentService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        electricalEquipmentRepository = Mock()
        electricalEquipmentMapper = Mock()
        electricalEquipmentService = new ElectricalEquipmentService(electricalEquipmentRepository, electricalEquipmentMapper)
    }

    def "GetAll"() {
        given:
        def electricalEquipmentsEntity1 = random.nextObject(ElectricalEquipmentsEntity)
        def electricalEquipmentsEntity2 = random.nextObject(ElectricalEquipmentsEntity)
        def electricalEquipmentsEntityList = [electricalEquipmentsEntity1,electricalEquipmentsEntity2]
        def electricalEquipmentsDto1 = random.nextObject(ElectricalEquipmentsDto)
        def electricalEquipmentsDto2 = random.nextObject(ElectricalEquipmentsDto)
        def electricalEquipmentsDtoList = [electricalEquipmentsDto1,electricalEquipmentsDto2]
        when:
        def result = electricalEquipmentService.getAll()
        then:
        1 * electricalEquipmentRepository.findAll() >> electricalEquipmentsEntityList
        1 * electricalEquipmentMapper.mapToDto(electricalEquipmentsEntity1) >> electricalEquipmentsDto1
        1 * electricalEquipmentMapper.mapToDto(electricalEquipmentsEntity2) >> electricalEquipmentsDto2
        result == electricalEquipmentsDtoList
    }

    def "GetById Successfully"() {
        given:
        Long electricalEquipmentId = 1L
        def electricalEquipmentEntity = random.nextObject(ElectricalEquipmentsEntity)
        def electricalEquipmentDto = random.nextObject(ElectricalEquipmentsDto)
        when:
        electricalEquipmentService.getById(electricalEquipmentId)
        then:
        1 * electricalEquipmentRepository.findById(electricalEquipmentId) >> Optional.of(electricalEquipmentEntity)
        1 * electricalEquipmentMapper.mapToDto(electricalEquipmentEntity) >> electricalEquipmentDto
    }

    def "GetById UnSuccessfully"() {
        given:
        Long electricalEquipmentId = 1L
        when:
        electricalEquipmentService.getById(electricalEquipmentId)
        then:
        1 * electricalEquipmentRepository.findById(electricalEquipmentId) >> Optional.empty()
        0 * electricalEquipmentMapper.mapToDto(_)
        def exception = thrown(RuntimeException)
        exception.message == "electricalEquipment not found"
    }

    def Add() {
        given:
        def electricalEquipmentDto = random.nextObject(ElectricalEquipmentsDto)
        def electricalEquipmentEntity = random.nextObject(ElectricalEquipmentsEntity)
        when:
        electricalEquipmentService.add(electricalEquipmentDto)
        then:
        1 * electricalEquipmentMapper.mapToEntity(electricalEquipmentDto) >> electricalEquipmentEntity
        1 * electricalEquipmentRepository.save(electricalEquipmentEntity)
    }

    def "UpdateElectricalEquipment Successfully"() {
        given:
        def electricalEquipmentDto = random.nextObject(ElectricalEquipmentsDto)
        Long electricalEquipmentId =1L
        def electricalEquipmentEntity = random.nextObject(ElectricalEquipmentsEntity)
        when:
        electricalEquipmentService.update(electricalEquipmentDto,electricalEquipmentId)
        then:
        1 * electricalEquipmentRepository.findById(electricalEquipmentId) >> Optional.of(electricalEquipmentEntity)
        1 * electricalEquipmentMapper.mapToEntity(electricalEquipmentEntity,electricalEquipmentDto) >> electricalEquipmentEntity
        1 * electricalEquipmentRepository.save(electricalEquipmentEntity)
    }
    def "UpdateElectricalEquipment Successfully"() {
        given:
        def electricalEquipmentDto = random.nextObject(ElectricalEquipmentsDto)
        Long electricalEquipmentId =1L
        when:
        electricalEquipmentService.update(electricalEquipmentDto,electricalEquipmentId)
        then:
        1 * electricalEquipmentRepository.findById(electricalEquipmentId) >> Optional.empty()
        0 * electricalEquipmentMapper.mapToEntity(_,_)
        0 * electricalEquipmentRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "ElectricalEquipment not found"
    }

    def Remove() {
        given:
        Long electricalEquipmentId = 1L
        when:
        electricalEquipmentService.remove(electricalEquipmentId)
        then:
        1 * electricalEquipmentRepository.deleteById(electricalEquipmentId)
    }
}
