package com.example.projecttrendshopapp.service.serviceImpl.user;

import com.example.projecttrendshopapp.dto.SendToUserBalanceDto;
import com.example.projecttrendshopapp.dto.UsersDto;

import java.util.List;

public interface UserService {
    List<UsersDto> getAll();

    UsersDto getById(Long userId);

    void add(UsersDto usersDto);

    void update(UsersDto usersDto, Long userId);

    void delete(Long userId);

    void removeCardFromUser(Long userId, Long cardId);

    void addCardToUser(Long userId, Long cardId);

    void sendToUserBalance(Long userId, SendToUserBalanceDto sendToUserBalanceDto);
}
