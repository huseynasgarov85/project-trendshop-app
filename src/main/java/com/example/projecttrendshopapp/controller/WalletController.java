package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.enums.PaymentMethod;
import com.example.projecttrendshopapp.service.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/pay")
    public void paymentMethod(@RequestParam PaymentMethod method,
                              @RequestParam Long order,
                              @RequestParam(required = false) Long card) {
        walletService.processPayment(method, order, card);
    }
}
