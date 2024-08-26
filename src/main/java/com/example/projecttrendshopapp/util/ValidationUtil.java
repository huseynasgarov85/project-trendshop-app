package com.example.projecttrendshopapp.util;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.DuplicateUserException;
import com.example.projecttrendshopapp.exception.InvalidBalanceException;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.exception.OutOfStockException;
import com.example.projecttrendshopapp.model.dto.UsersDto;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidationUtil {
    private final UsersRepository usersRepository;
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final OrderRepository orderRepository;
    private final CardsRepository cardsRepository;


    public void checkUserEmail(UsersDto usersDto) {
        var user = usersRepository.findByEmailOrUsername(usersDto.getEmail(),usersDto.getUsername());
        if (user.isPresent()) {
            throw new DuplicateUserException("Change your user email");
        }
    }

    public void checkStockSize(Long orderId) {
        var orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("orderId not found"));
        List<BasketEntity> filteredStatus = orderEntity.getBaskets().stream().filter(it -> it.getStatus().equals(Status.SELECTED)).toList();
        for (BasketEntity basketEntity : filteredStatus) {
            if (basketEntity.getProductName().equals(Products.SHOES)) {
                var shoesEntity = shoesRepository.findById(basketEntity.getProductId()).orElseThrow();
                if (shoesEntity.getCount() <= 0) throw new OutOfStockException("Shoes are out of stock");
            } else if (basketEntity.getProductName().equals(Products.SHIRT)) {
                var shirtEntity = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow();
                if (shirtEntity.getCounter() <= 0) throw new OutOfStockException("Shirt are out of stock");
            } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                var trousersEntity = trousersRepository.findById(basketEntity.getProductId()).orElseThrow();
                if (trousersEntity.getCounter() <= 0) throw new OutOfStockException("Trousers are out of stock");
            } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var electricalEquipmentsEntity = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow();
                if (electricalEquipmentsEntity.getCount() <= 0)
                    throw new OutOfStockException("ElectricalEquipments are out of stock");
            }
        }
    }

    public void checkPaymentByCard(Long orderId, Long cardId) {
        var amount = checkProductAndBalance(orderId);
        var cardEntity = cardsRepository.findById(cardId).orElseThrow(() -> new NotFoundException("cardId not found"));
        if (amount > cardEntity.getCardBalance()) {
            throw new InvalidBalanceException("insufficient funds");
        }
    }

    public void checkPaymentByApplication(Long orderId) {
        var orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("orderId not found"));
       var amount = checkProductAndBalance(orderId);
        if (amount > orderEntity.getUsers().getBalance()) {
            throw new InvalidBalanceException("insufficient funds");
        }
    }

    public Double checkProductAndBalance(Long orderId) {
        var orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("orderId not found"));
        Double counter = 0.0;
        for (BasketEntity basket : orderEntity.getBaskets()) {
            if (basket.getProductName().equals(Products.SHIRT)) {
                var shirtEntity = shirtsRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("shirtId not found"));
                counter += shirtEntity.getBalance();
            } else if (basket.getProductName().equals(Products.SHOES)) {
                var shoesEntity = shoesRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("shoeId not found"));
                counter += shoesEntity.getBalance();
            } else if (basket.getProductName().equals(Products.TROUSERS)) {
                var trousersEntity = trousersRepository.findById(basket.getProductId()).orElseThrow((() -> new NotFoundException("trouserId not found")));
                counter += trousersEntity.getPrice();
            } else if (basket.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var electricalEquipment = electricalEquipmentRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("electricalEquipmentId not found"));
                counter += electricalEquipment.getPrice();
            }
        }
        return counter;
    }

}
