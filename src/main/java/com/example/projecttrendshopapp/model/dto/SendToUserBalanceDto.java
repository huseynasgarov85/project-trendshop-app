package com.example.projecttrendshopapp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendToUserBalanceDto {
    private Long cardId;
    @NotNull
    @PositiveOrZero
    private Double balance;
}
