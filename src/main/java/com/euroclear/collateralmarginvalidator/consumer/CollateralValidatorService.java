package com.euroclear.collateralmarginvalidator.consumer;

import com.euroclear.collateralmarginvalidator.dto.MarginCallAlert;
import com.euroclear.collateralmarginvalidator.dto.PortfolioUpdate;
import com.euroclear.collateralmarginvalidator.model.MarginCallEvent;
import com.euroclear.collateralmarginvalidator.repository.MarginCallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollateralValidatorService {

    private final KafkaTemplate<String, MarginCallAlert> kafkaTemplate;
    private final MarginCallRepository marginCallRepository;

    @KafkaListener(topics = "portfolio-update", groupId = "collateral-validation-group")
    public void processPortfolioUpdate(PortfolioUpdate portfolioUpdate) {

        log.info("Processing portfolio update: {} with {}:", portfolioUpdate.getBankId(), portfolioUpdate.getAssetId());

        if (portfolioUpdate.getCurrentValue().compareTo(portfolioUpdate.getMarginCallMinValue()) < 0) {

            BigDecimal diff = portfolioUpdate.getMarginCallMinValue().subtract(portfolioUpdate.getCurrentValue());
            log.warn("MARGIN CALL | {} has a shortfall of {}!\n", portfolioUpdate.getBankId(), diff);

            MarginCallAlert alert = new MarginCallAlert(
                    portfolioUpdate.getBankId(),
                    diff,
                    LocalDateTime.now()
            );
            kafkaTemplate.send("margin-call", portfolioUpdate.getBankId(), alert);

            MarginCallEvent event = new MarginCallEvent(null, portfolioUpdate.getBankId(), diff, LocalDateTime.now());
            marginCallRepository.save(event);
            log.info("Saved Margin Call event to PostgreSQL for: {}", portfolioUpdate.getBankId());

        } else {

            log.info("Skipping portfolio validation");
        }
    }
}
