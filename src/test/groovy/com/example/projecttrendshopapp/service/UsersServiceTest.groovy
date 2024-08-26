package com.example.projecttrendshopapp.service

import com.example.projecttrendshopapp.dao.entity.CardsEntity
import com.example.projecttrendshopapp.dao.entity.RoleEntity
import com.example.projecttrendshopapp.dao.entity.UsersEntity
import com.example.projecttrendshopapp.dao.repository.CardsRepository
import com.example.projecttrendshopapp.dao.repository.UsersRepository
import com.example.projecttrendshopapp.mapper.UsersMapper
import com.example.projecttrendshopapp.model.dto.SendToUserBalanceDto
import com.example.projecttrendshopapp.model.dto.UsersDto
import com.example.projecttrendshopapp.service.services.UsersService
import com.example.projecttrendshopapp.util.ValidationUtil
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class UsersServiceTest extends Specification {
    private UsersRepository usersRepository
    private UsersMapper usersMapper
    private CardsRepository cardsRepository
    private ValidationUtil validationUtil
    private PasswordEncoder passwordEncoder
    private UsersService usersService
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        usersRepository = Mock()
        usersMapper = Mock()
        cardsRepository = Mock()
        validationUtil = Mock()
        passwordEncoder = Mock()
        usersService = new UsersService(usersRepository, usersMapper, cardsRepository, validationUtil, passwordEncoder)
    }

    def GetAll() {
        given:
        def usersEntity1 = random.nextObject(UsersEntity)
        def usersEntity2 = random.nextObject(UsersEntity)
        def usersEntityList = [usersEntity1, usersEntity2]
        def usersDto1 = random.nextObject(UsersDto)
        def usersDto2 = random.nextObject(UsersDto)
        def usersDtoList = [usersDto1, usersDto2]
        when:
        def result = usersService.getAll()
        then:
        1 * usersRepository.findAll() >> usersEntityList
        1 * usersMapper.mapToDto(usersEntity1) >> usersDto1
        1 * usersMapper.mapToDto(usersEntity2) >> usersDto2
        result == usersDtoList
    }

    def "GetById Successfully"() {
        given:
        Long userId = 1L
        def userEntity = random.nextObject(UsersEntity)
        def userDto = random.nextObject(UsersDto)
        when:
        usersService.getById(userId)
        then:
        1 * usersRepository.findById(userId) >> Optional.of(userEntity)
        1 * usersMapper.mapToDto(userEntity) >> userDto
    }

    def "GetById UnSuccessfully"() {
        given:
        Long userId = 1L
        when:
        usersService.getById(userId)
        then:
        1 * usersRepository.findById(userId) >> Optional.empty()
        0 * usersMapper.mapToDto(_)
        def exception = thrown(RuntimeException)
        exception.message == "userId not found"
    }

    def "Add"() {
        given:
        def userEntity = random.nextObject(UsersEntity)
        def userDto = random.nextObject(UsersDto)
        def roleEntity = random.nextObject(RoleEntity)
        roleEntity.name = "ROLE_USER"
        userEntity.setRoles(List.of(roleEntity))

        when:
        usersService.add(userDto)

        then:
        1 * validationUtil.checkUserEmail(userDto)
        1 * usersMapper.mapToEntity(userDto) >> userEntity
        1 * usersRepository.save(userEntity)
    }

    def "UpdateUser Successfully"() {
        given:
        Long userId = 1L
        def usersDto = random.nextObject(UsersDto)
        def usersEntity = random.nextObject(UsersEntity)
        when:
        usersService.update(usersDto, userId)
        then:
        1 * usersRepository.findById(userId) >> Optional.of(usersEntity)
        1 * usersMapper.mapToEntity(usersEntity, usersDto) >> usersEntity
        1 * usersRepository.save(usersEntity)
    }

    def "UpdateUser UnSuccessfully"() {
        given:
        Long userId = 1L
        def usersDto = random.nextObject(UsersDto)
        when:
        usersService.update(usersDto, userId)
        then:
        1 * usersRepository.findById(userId) >> Optional.empty()
        0 * usersMapper.mapToEntity(_, _)
        0 * usersRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "userId not found"
    }

    def Delete() {
        given:
        Long userId = 1L
        when:
        usersService.delete(userId)
        then:
        1 * usersRepository.deleteById(userId)
    }

    def "RemoveCardFromUser Successfully"() {
        given:
        Long userId = 1L
        Long cardId = 1L
        def userEntity = random.nextObject(UsersEntity)
        def cardsEntity1 = random.nextObject(CardsEntity)
        def cardsEntity2 = random.nextObject(CardsEntity)
        cardsEntity1.setId(cardId)
        userEntity.setCards([cardsEntity1, cardsEntity2])
        when:
        usersService.removeCardFromUser(userId, cardId)
        then:
        1 * usersRepository.findById(userId) >> Optional.of(userEntity)
        1 * usersRepository.save(_ as UsersEntity) >> { UsersEntity savedEntity ->
            assert savedEntity.getCards().size() == 1
            assert !savedEntity.getCards().any { it.id == cardId }
        }
    }

    def "RemoveCardFromUser UnSuccessfully"() {
        given:
        Long userId = 1L
        Long cardId = 1L
        def userEntity = random.nextObject(UsersEntity)
        def cardsEntity1 = random.nextObject(CardsEntity)
        def cardsEntity2 = random.nextObject(CardsEntity)
        cardsEntity1.setId(cardId)
        userEntity.setCards([cardsEntity1, cardsEntity2])
        when:
        usersService.removeCardFromUser(userId, cardId)
        then:
        1 * usersRepository.findById(userId) >> Optional.empty()
        0 * usersRepository.save(_)
        def exception = thrown(RuntimeException)
        exception.message == "userId not found"
    }

    def "AddCardToUser"() {
        given:
        Long userId = 1L
        Long cardId = 1L
        def userEntity = random.nextObject(UsersEntity)
        def cardEntity = random.nextObject(CardsEntity)
        cardEntity.setUsers(userEntity)
        when:
        usersService.addCardToUser(userId, cardId)
        then:
        1 * usersRepository.findById(userId) >> Optional.of(userEntity)
        1 * cardsRepository.findById(cardId) >> Optional.of(cardEntity)
        1 * usersRepository.save(userEntity)
    }

    def "should successfully send balance to user"() {
        given:
        Long userId = 1L
        Long cardId = 2L
        BigDecimal balanceToSend = new BigDecimal("100.00")
        SendToUserBalanceDto sendToUserBalanceDto = new SendToUserBalanceDto(cardId: cardId, balance: balanceToSend)
        UsersEntity userEntity = new UsersEntity(id: userId, balance: new BigDecimal("200.00"))
        CardsEntity cardEntity = new CardsEntity(id: cardId, cardBalance: new BigDecimal("150.00"))

        when:
        usersService.sendToUserBalance(userId, sendToUserBalanceDto)


        then:
        1 * usersRepository.findById(userId) >> Optional.of(userEntity)
        1 * cardsRepository.findById(cardId) >> Optional.of(cardEntity)
        1 * cardsRepository.save(cardEntity)
        1 * usersRepository.save(userEntity)
    }

}