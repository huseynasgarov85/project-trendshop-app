package com.example.projecttrendshopapp.service.serviceImpl.wallet;

import com.example.projecttrendshopapp.enums.PaymentMethod;

public interface WalletService {
    void paymentMethod(PaymentMethod method, Long order, Long card);
}
