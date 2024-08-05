package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.SendToUserBalanceDto;
import com.example.projecttrendshopapp.model.dto.UsersDto;
import com.example.projecttrendshopapp.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UsersDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UsersDto getById(@PathVariable Long userId) {
        return usersService.getById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid UsersDto usersDto) {
        usersService.addUser(usersDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public void updateUser(@RequestBody @Valid UsersDto usersDto, @PathVariable Long userId) {
        usersService.updateUser(usersDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
    }

    @DeleteMapping("/{userId}/cards/{cardId}")
    public void removeCardFromUser(@PathVariable Long userId, @PathVariable Long cardId) {
        usersService.removeCardFromUser(userId, cardId);
    }

    @PatchMapping("/{userId}/cards/{cardId}")
    public void addCardToUser(@PathVariable Long userId,@PathVariable Long cardId){
        usersService.addCardToUser(userId,cardId);
    }

    @PatchMapping("/{userId}/balance")
    public void sendToUserBalance(@PathVariable Long userId, SendToUserBalanceDto sendToUserBalanceDto){
        usersService.sendToUserBalance(userId,sendToUserBalanceDto);
    }
}
