package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.dao.repository.OrderRepository;
import com.example.projecttrendshopapp.model.enums.PaymentMethod;
import com.example.projecttrendshopapp.service.OrderService;
import com.example.projecttrendshopapp.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/select/order/{orderId}/card/{cardId}")
    public void paymentMethod(@RequestParam PaymentMethod method, @PathVariable Long orderId, @PathVariable Long cardId) {
        walletService.processPayment(method, orderId, cardId);
    }
}
