package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.dto.CardsDto;
import com.example.projecttrendshopapp.service.serviceImpl.card.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<CardsDto> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/{cardId}")
    public CardsDto getById(@PathVariable Long cardId) {
        return cardService.getById(cardId);
    }

    @PostMapping
    public void add(@RequestBody @Valid CardsDto cardsDto) {
        cardService.add(cardsDto);
    }

    @PutMapping("/{cardId}")
    public void update(@RequestBody @Valid CardsDto cardsDto, @PathVariable Long cardId) {
        cardService.update(cardsDto, cardId);
    }

    @DeleteMapping("/{cardId}")
    public void remove(@PathVariable Long cardId) {
        cardService.remove(cardId);
    }

    @PostMapping("/{userId}")
    public void addCardsToUser(@PathVariable Long userId, @RequestBody @Valid CardsDto cardsDto) {
        cardService.addCardsToUser(userId, cardsDto);
    }
}
