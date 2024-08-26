package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.SendToUserBalanceDto;
import com.example.projecttrendshopapp.model.dto.UsersDto;
import com.example.projecttrendshopapp.service.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public List<UsersDto> getAll() {
        return usersService.getAll();
    }

    @GetMapping("/{userId}")
    public UsersDto getById(@PathVariable Long userId) {
        return usersService.getById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid UsersDto usersDto) {
        usersService.add(usersDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public void update(@RequestBody @Valid UsersDto usersDto, @PathVariable Long userId) {
        usersService.update(usersDto, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        usersService.delete(userId);
    }

    @DeleteMapping("/{userId}/{cardId}")
    public void removeCardFromUser(@PathVariable Long userId, @PathVariable Long cardId) {
        usersService.removeCardFromUser(userId, cardId);
    }

    @PatchMapping("/{userId}/{cardId}")
    public void addCardToUser(@PathVariable Long userId,@PathVariable Long cardId){
        usersService.addCardToUser(userId,cardId);
    }

    @PatchMapping("/balance/{userId}")
    public void sendToUserBalance(@PathVariable Long userId, SendToUserBalanceDto sendToUserBalanceDto){
        usersService.sendToUserBalance(userId,sendToUserBalanceDto);
    }




}
