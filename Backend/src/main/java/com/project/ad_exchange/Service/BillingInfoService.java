package com.project.ad_exchange.Service;

import com.project.ad_exchange.Dto.BillingInfoDto;
import com.project.ad_exchange.Model.BillingInfo;
import com.project.ad_exchange.Model.User;
import com.project.ad_exchange.Repository.BillingInfoRepository;
import com.project.ad_exchange.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingInfoService {
    @Autowired
    private BillingInfoRepository billingInfoRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add or update card information for a user's billing info using BillingInfoDto.
     */
    public BillingInfo addOrUpdateCardInfo(Long userId, BillingInfoDto billingInfoDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        BillingInfo billingInfo = billingInfoRepository.findByUserId(userId).orElse(new BillingInfo());

        billingInfo.setUser(user);
        billingInfo.setCardNumber(billingInfoDto.cardNumber());
        billingInfo.setCardHolderName(billingInfoDto.cardHolderName());
        billingInfo.setExpirationDate(billingInfoDto.expirationMonth() + "/" + billingInfoDto.expirationYear());

        return billingInfoRepository.save(billingInfo);
    }


    /**
     * Fetch the card information for a user as a BillingInfoDto.
     */
    public BillingInfoDto getCardInfo(Long userId) {
        BillingInfo billingInfo = billingInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("No billing info found for user ID: " + userId));

        // Convert BillingInfo to BillingInfoDto
        String[] expirationParts = billingInfo.getExpirationDate().split("/");
        String expirationMonth = expirationParts.length > 0 ? expirationParts[0] : null;
        String expirationYear = expirationParts.length > 1 ? expirationParts[1] : null;

        return new BillingInfoDto(
                billingInfo.getCardHolderName(),
                billingInfo.getCardNumber(),
                expirationMonth,
                expirationYear
        );
    }

    /**
     * Remove card information (but keep other billing info).
     */
    public BillingInfoDto clearCardInfo(Long userId) {
        BillingInfo billingInfo = billingInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("No billing info found for user ID: " + userId));

        // Clear only card-related fields
        billingInfo.setCardNumber(null);
        billingInfo.setCardHolderName(null);
        billingInfo.setExpirationDate(null);

        billingInfoRepository.save(billingInfo);

        // Return an empty BillingInfoDto
        return new BillingInfoDto(null, null, null, null);
    }
}
