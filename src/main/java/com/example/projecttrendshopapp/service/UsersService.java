package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.repository.CardsRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.InvalidBalanceException;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.UsersMapper;
import com.example.projecttrendshopapp.model.dto.SendToUserBalanceDto;
import com.example.projecttrendshopapp.model.dto.UsersDto;
import com.example.projecttrendshopapp.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final CardsRepository cardsRepository;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;


    public List<UsersDto> getAllUsers() {
        log.info("ActionLog.getAllUsers.started");
        var usersEntity = usersRepository.findAll();
        var usersDto = usersEntity.stream().map(usersMapper::mapToDto).toList();
        log.info("ActionLog.getAllUsers.started");
        return usersDto;
    }

    public UsersDto getById(Long userId) {
        log.info("ActionLog.getById.started:userId {}", userId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var userDto = usersMapper.mapToDto(userEntity);
        log.info("ActionLog.getById.end:userId {}", userId);
        return userDto;
    }

    public void addUser(UsersDto userDto) {
        validationUtil.checkUserEmail(userDto);
        log.info("ActionLog.addUser.started:userDto {}", userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var userEntity = usersMapper.mapToEntity(userDto);
        userEntity.setDateOfCreation(LocalDate.now());
        var role = new RoleEntity(null, "ROLE_USER", userEntity);
        userEntity.setRoles(List.of(role));
        usersRepository.save(userEntity);
        log.info("ActionLog.addUser.end:userDto {}", userDto);
    }

    public void updateUser(UsersDto usersDto, Long userId) {
        log.info("ActionLog.updateUser.started: usersDto {},userId {}", usersDto, userId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var updateUser = usersMapper.mapToEntity(userEntity, usersDto);
        usersRepository.save(updateUser);
        log.info("ActionLog.updateUser.started: usersDto {},userId {}", usersDto, userId);
    }

    public void deleteUser(Long userId) {
        log.info("ActionLog.deleteUser.started:userId {}", userId);
        usersRepository.deleteById(userId);
        log.info("ActionLog.deleteUser.started:userId {}", userId);
    }

    public void removeCardFromUser(Long userId, Long cardId) {
        log.info("ActionLog.removeCardFromUser.started:userId {},cardId {}", userId, cardId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var cards = userEntity.getCards();
        cards.removeIf(m -> m.getId().equals(cardId));
        userEntity.setCards(cards);
        usersRepository.save(userEntity);
        log.info("ActionLog.removeCardFromUser.end:userId {},cardId {}", userId, cardId);
    }

    public void addCardToUser(Long userId, Long cardId) {
        log.info("ActionLog.addCardToUser.started:userId {},cardId {}", userId, cardId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> new NotFoundException("cardId not found"));
        var cards = userEntity.getCards();
        cardEntity.setUsers(userEntity);
        cards.add(cardEntity);
        usersRepository.save(userEntity);
        log.info("ActionLog.addCardToUser.end:userId {},cardId {}", userId, cardId);
    }

    public void sendToUserBalance(Long userId, SendToUserBalanceDto sendToUserBalanceDto) {
        log.info("ActionLog.sendToBalance.started:userId {},sendToUserBalanceDto {}", userId, sendToUserBalanceDto);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var card = cardsRepository.findById(sendToUserBalanceDto.getCardId()).orElseThrow(() -> new NotFoundException(" sendToUserBalanceDto.getCardId() not found"));
        if (sendToUserBalanceDto.getBalance() > card.getCardBalance()) {
            throw new InvalidBalanceException("insufficient balance");
        }
        card.setCardBalance(card.getCardBalance() - sendToUserBalanceDto.getBalance());
        userEntity.setBalance(sendToUserBalanceDto.getBalance() + userEntity.getBalance());
        usersRepository.save(userEntity);
        log.info("ActionLog.sendToBalance.end:userId {},sendToUserBalanceDto {}", userId, sendToUserBalanceDto);
    }

}
