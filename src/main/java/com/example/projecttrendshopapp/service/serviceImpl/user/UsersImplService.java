package com.example.projecttrendshopapp.service.serviceImpl.user;

import com.example.projecttrendshopapp.dao.entity.CardsEntity;
import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.repository.CardsRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.dto.SendToUserBalanceDto;
import com.example.projecttrendshopapp.dto.UsersDto;
import com.example.projecttrendshopapp.exception.InvalidBalanceException;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.UsersMapper;
import com.example.projecttrendshopapp.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersImplService implements UserService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final CardsRepository cardsRepository;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UsersDto> getAll() {
        log.info("ActionLog.getAll.started");
        var usersEntity = usersRepository.findAll();
        var usersDto = usersEntity.stream().map(usersMapper::mapToDto).toList();
        log.info("ActionLog.getAll.started");
        return usersDto;
    }

    @Override
    public UsersDto getById(Long userId) {
        log.info("ActionLog.getById.started:userId {}", userId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var userDto = usersMapper.mapToDto(userEntity);
        log.info("ActionLog.getById.end:userId {}", userId);
        return userDto;
    }

    @Override
    public void add(UsersDto userDto) {
        validationUtil.checkUserEmail(userDto);
        log.info("ActionLog.add.started:userDto {}", userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var userEntity = usersMapper.mapToEntity(userDto);
        userEntity.setDateOfCreation(LocalDate.now());
        var role = new RoleEntity(null, "ROLE_USER", userEntity);
        userEntity.setRoles(List.of(role));
        usersRepository.save(userEntity);
        log.info("ActionLog.add.end:userDto {}", userDto);
    }

    @Override
    public void update(UsersDto usersDto, Long userId) {
        log.info("ActionLog.update.started: usersDto {},userId {}", usersDto, userId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var updateUser = usersMapper.mapToEntity(userEntity, usersDto);
        usersRepository.save(updateUser);
        log.info("ActionLog.update.started: usersDto {},userId {}", usersDto, userId);
    }

    @Override
    public void delete(Long userId) {
        log.info("ActionLog.delete.started:userId {}", userId);
        usersRepository.deleteById(userId);
        log.info("ActionLog.delete.started:userId {}", userId);
    }

    @Override
    public void removeCardFromUser(Long userId, Long cardId) {
        log.info("ActionLog.removeCardFromUser.started:userId {},cardId {}", userId, cardId);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        List<CardsEntity> cards = userEntity.getCards();
        cards.removeIf(m -> m.getId().equals(cardId));
        userEntity.setCards(cards);
        usersRepository.save(userEntity);
        log.info("ActionLog.removeCardFromUser.end:userId {},cardId {}", userId, cardId);
    }

    @Override
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void sendToUserBalance(Long userId, SendToUserBalanceDto sendToUserBalanceDto) {
        log.info("ActionLog.sendToBalance.started:userId {},sendToUserBalanceDto {}", userId, sendToUserBalanceDto);
        var userEntity = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("userId not found"));
        var card = cardsRepository.findById(sendToUserBalanceDto.getCardId()).orElseThrow(() -> new NotFoundException(" sendToUserBalanceDto.getCardId() not found"));
        if (sendToUserBalanceDto.getBalance() > card.getCardBalance()) {
            throw new InvalidBalanceException("insufficient balance");
        }
        card.setCardBalance(card.getCardBalance() - sendToUserBalanceDto.getBalance());
        userEntity.setBalance(sendToUserBalanceDto.getBalance() + userEntity.getBalance());
        cardsRepository.save(card);
        usersRepository.save(userEntity);
        log.info("ActionLog.sendToBalance.end:userId {},sendToUserBalanceDto {}", userId, sendToUserBalanceDto);
    }

}
