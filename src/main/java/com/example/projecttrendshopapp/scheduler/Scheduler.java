package com.example.projecttrendshopapp.scheduler;

import com.example.projecttrendshopapp.dao.repository.OtpRepository;
import com.example.projecttrendshopapp.enums.OtpStatus;
import com.example.projecttrendshopapp.service.serviceImpl.order.OrderImplService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {
    private final OrderImplService orderImplService;
    private final OtpRepository otpRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void cashBackScheduler() {
        orderImplService.generateCashBack().forEach(it -> {
            log.info("Congratulations, since the value of your face is more than 500 manats, userId: {} will receive 5 percent cashback and free shipping,balance is: {}", it.getId(), it.getBalance());
        });
    }

    @Scheduled(fixedRate = 1 * 60 * 1000)
    public void checkOtpStatus() {
        LocalDateTime now = LocalDateTime.now();
        otpRepository.findAll().forEach(it -> {
            if (it.getOtpDate().plusMinutes(2).isBefore(now)) {
                it.setOtpStatus(OtpStatus.INACTIVE);
                otpRepository.save(it);
            }
        });
        log.info("ActionLog.changed active status in inactive");
    }
}
