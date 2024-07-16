package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.CardsDto;
import com.example.projecttrendshopapp.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
public class CardController {
    private final CardService cardService;

    @GetMapping()
    public List<CardsDto> getAll(){
        return cardService.getAll();
    }
    @GetMapping("/{cardId}")
    public CardsDto getById(@PathVariable Long cardId){
        return cardService.getById(cardId);
    }
    @PostMapping()
    public void addCards(@RequestBody @Valid CardsDto cardsDto){
        cardService.addCard(cardsDto);
    }
    @PutMapping("/{cardId}")
    public void updateCard(@RequestBody @Valid CardsDto cardsDto,@PathVariable Long cardId){
        cardService.updateCard(cardsDto,cardId);
    }
    @DeleteMapping("/{cardId}")
    public void removeCard(@PathVariable Long cardId){
        cardService.removeCard(cardId);
    }
    @PostMapping("/users/{userId}")
    public void addCardsToUser(@PathVariable Long userId,@RequestBody @Valid CardsDto cardsDto){
        cardService.addCardsToUser(userId,cardsDto);
    }
}
