package com.example.projecttrendshopapp.service.serviceImpl.wallet;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.enums.PaymentMethod;
import com.example.projecttrendshopapp.enums.Products;
import com.example.projecttrendshopapp.enums.Status;
import com.example.projecttrendshopapp.exception.ExpiredCardException;
import com.example.projecttrendshopapp.exception.IllegalStateException;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.service.serviceImpl.email.EmailService;
import com.example.projecttrendshopapp.service.serviceImpl.order.OrderImplService;
import com.example.projecttrendshopapp.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletImplService implements WalletService {
    private final OrderRepository orderRepository;
    private final ShoesRepository shoesRepository;
    private final ShirtsRepository shirtsRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final ValidationUtil validationUtil;
    private final CardsRepository cardsRepository;
    private final OrderImplService orderImplService;
    private final EmailService emailService;

    @Override
    @Transactional
    public void paymentMethod(PaymentMethod method, Long order, Long card) {
        log.info("ActionLog.processPayment.started:orderId {},cardId {},method {}", order, card, method);
        List<BasketEntity> filteredBasketEntities = orderRepository.findById(order)
                .orElseThrow(() -> new NotFoundException("orderId not found")).getBaskets()
                .stream().filter(it -> it.getStatus().equals(Status.SELECTED)).toList();
        switch (method) {
            case CARD:
                paymentByCard(order, card, filteredBasketEntities);
                emailService.sendToSuccessPayment(filteredBasketEntities);
                break;
            case APPLICATION:
                paymentByApplication(order, filteredBasketEntities);
                emailService.sendToSuccessPayment(filteredBasketEntities);
                break;
            default:
                throw new IllegalStateException("Unexpected value:" + method);
        }
        log.info("ActionLog.processPayment.end:orderId {},cardId {},method {}", order, card, method);
    }

    private void paymentByCard(Long orderId, Long cardId, List<BasketEntity> filteredBasketEntities) {
        validationUtil.checkPaymentByCard(orderId, cardId);
        var amount = generalPayment(filteredBasketEntities);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> new NotFoundException("cardId not found"));
        var orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("orerId not found"));
        if (cardEntity.getExpireDate().isBefore(LocalDate.now())) {
            throw new ExpiredCardException("The card has expired.");
        }
        if (orderEntity.getProductsPrice() > 150){
            orderEntity.setFreeDelivery(Boolean.TRUE);
        }
        if (amount < cardEntity.getCardBalance()) {
            OrderEntity order = orderRepository.findById(orderId).orElseThrow();
            order.setProductsPrice(amount);
            orderRepository.save(order);
            cardEntity.setCardBalance(cardEntity.getCardBalance() - amount);
        }
        orderImplService.generalStock(filteredBasketEntities);
    }

    private void paymentByApplication(Long orderId, List<BasketEntity> filteredBasketEntities) {
        validationUtil.checkPaymentByApplication(orderId);
        orderImplService.generalStock(filteredBasketEntities);
        var orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("orderId not found"));
        var amount = generalPayment(filteredBasketEntities);
        if (amount < orderEntity.getUsers().getBalance()) {
            OrderEntity order = orderRepository.findById(orderId).orElseThrow();
            order.setProductsPrice(amount);
            orderRepository.save(order);
            orderEntity.getUsers().setBalance(orderEntity.getUsers().getBalance() - amount);
        }
    }

    private Double generalPayment(List<BasketEntity> filteredList) {
        Double amount = 0.0;
        for (BasketEntity basket : filteredList) {
            if (basket.getProductName().equals(Products.SHIRT)) {
                var shirtEntity = shirtsRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("shirtId not found"));
                amount += shirtEntity.getBalance();
            } else if (basket.getProductName().equals(Products.SHOES)) {
                var shoesEntity = shoesRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("shoeId not found"));
                amount += shoesEntity.getBalance();
            } else if (basket.getProductName().equals(Products.TROUSERS)) {
                var trousersEntity = trousersRepository.findById(basket.getProductId()).orElseThrow((() -> new NotFoundException("trouserId not found")));
                amount += trousersEntity.getPrice();
            } else if (basket.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var electricalEquipment = electricalEquipmentRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("electricalEquipmentId not found"));
                amount += electricalEquipment.getPrice();
            }
        }
        return amount;
    }

}
