package com.euroclear.collateralmarginvalidator.producer;

import com.euroclear.collateralmarginvalidator.dto.PortfolioUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioUpdateProduser {

    private final KafkaTemplate<String, PortfolioUpdate> kafkaTemplate;

    private Random random = new Random();
    private List<String> banks = List.of("Bank 1", "Bank 2", "Bank 3", "Bank 4", "Bank 5");
    private List<String> assets = List.of("Asset 1", "Asset 2", "Asset 3", "Asset 4");

    @Scheduled(fixedRate = 2000)
    public void generatePortfolioUpdate() {
        String bankId = banks.get(random.nextInt(banks.size()));
        String assetId = assets.get(random.nextInt(assets.size()));

        //Margin Call only below $100k value
        BigDecimal marginCallMinValue = new BigDecimal("100000");

        //Prises will rise or fall between values 80k and 120k
        double price = 80000 + Math.random() * 40000;
        BigDecimal currentValue = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);

        PortfolioUpdate portfolioUpdate = new PortfolioUpdate(bankId, assetId, marginCallMinValue, currentValue);
        kafkaTemplate.send("portfolio-update", bankId, portfolioUpdate);

        log.info("Generated portfolio update and send to Kafka: {} and {}", bankId, assetId);
    }
}
