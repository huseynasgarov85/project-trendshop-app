package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.enums.PaymentMethod;
import com.example.projecttrendshopapp.service.serviceImpl.wallet.WalletImplService;
import com.example.projecttrendshopapp.service.serviceImpl.wallet.WalletService;
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
        walletService.paymentMethod(method, order, card);
    }
}
