package com.example.projecttrendshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendToUserBalanceDto {
    private Long cardId;
    private Double balance;
}
