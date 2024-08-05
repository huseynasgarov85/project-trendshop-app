package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.BasketEntity
import com.example.projecttrendshopapp.dao.entity.ElectricalEquipmentsEntity
import com.example.projecttrendshopapp.dao.entity.OrderEntity
import com.example.projecttrendshopapp.dao.entity.ShirtEntity
import com.example.projecttrendshopapp.dao.entity.ShoesEntity
import com.example.projecttrendshopapp.dao.entity.TrousersEntity
import com.example.projecttrendshopapp.dao.repository.BasketRepository
import com.example.projecttrendshopapp.dao.repository.ElectricalEquipmentRepository
import com.example.projecttrendshopapp.dao.repository.OrderRepository
import com.example.projecttrendshopapp.dao.repository.ShirtsRepository
import com.example.projecttrendshopapp.dao.repository.ShoesRepository
import com.example.projecttrendshopapp.dao.repository.TrousersRepository
import com.example.projecttrendshopapp.dao.repository.UsersRepository
import com.example.projecttrendshopapp.mapper.BasketMapper
import com.example.projecttrendshopapp.mapper.OrderMapper
import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDtoVol2
import com.example.projecttrendshopapp.model.dto.OrderDto
import com.example.projecttrendshopapp.model.dto.ShirtDtoVol2
import com.example.projecttrendshopapp.model.dto.ShoesDtoVol2
import com.example.projecttrendshopapp.model.dto.TrousersDtoVol2
import com.example.projecttrendshopapp.util.ValidationUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class OrderServiceTest extends Specification {
    private OrderRepository orderRepository
    private OrderMapper orderMapper
    private BasketRepository basketRepository
    private ShirtsRepository shirtsRepository
    private ShoesRepository shoesRepository
    private TrousersRepository trousersRepository
    private ElectricalEquipmentRepository electricalEquipmentRepository
    private BasketMapper basketMapper
    private ValidationUtil validationUtil
    private UsersRepository usersRepository
    private OrderService orderService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        orderRepository = Mock()
        orderMapper = Mock()
        basketRepository = Mock()
        shirtsRepository = Mock()
        shoesRepository = Mock()
        trousersRepository = Mock()
        electricalEquipmentRepository = Mock()
        basketMapper = Mock()
        validationUtil = Mock()
        usersRepository = Mock()
        orderService = new OrderService(orderRepository, orderMapper, basketRepository, shirtsRepository, shoesRepository, trousersRepository, electricalEquipmentRepository, basketMapper, validationUtil, usersRepository)

    }

    def "getAllInformation"() {
        given:
        def orderEntity1 = random.nextObject(OrderEntity)
        def orderEntity2 = random.nextObject(OrderEntity)
        def orderEntityList = [orderEntity1, orderEntity2]
        def orderDto1 = random.nextObject(OrderDto)
        def orderDto2 = random.nextObject(OrderDto)
        def orderDtoList = [orderDto1, orderDto2]
        def shirtEntity = random.nextObject(ShirtEntity)
        def trousersEntity = random.nextObject(TrousersEntity)
        def shoesEntity = random.nextObject(ShoesEntity)
        def electricalEquipmentEntity = random.nextObject(ElectricalEquipmentsEntity)
        def shirtDto = random.nextObject(ShirtDtoVol2)
        def trousersDto = random.nextObject(TrousersDtoVol2)
        def shoesDto = random.nextObject(ShoesDtoVol2)
        def electricalEquipmentDto = random.nextObject(ElectricalEquipmentsDtoVol2)
        def orderDto = random.nextObject(OrderDto)
        def basketEntity = random.nextObject(BasketEntity)

        when:
        def result = orderService.getAllInformation()

        then:
        1 * orderRepository.findAll() >> orderEntityList
        1 * orderMapper.mapToDto(orderEntity1) >> orderDto1
        1 * orderMapper.mapToDto(orderEntity2) >> orderDto2
        1 * shirtsRepository.findById(basketEntity.getId()) >> shirtEntity
        1 * trousersRepository.findById(basketEntity.getId()) >> trousersEntity
        1 * shoesRepository.findById(basketEntity.getId()) >> shoesEntity
        1 * electricalEquipmentRepository.findById(basketEntity.getId()) >> electricalEquipmentEntity
        1 * orderMapper.mapToDto(orderEntity1) >> orderDto
        1 * basketMapper.mapShirtEntityToDto()

        result == orderDtoList
    }
//    def "CreateOrder"() {
//
//    }
//
//    def "GetById"() {
//
//    }
//
//    def "RemoveOrder"() {
//
//    }
}

