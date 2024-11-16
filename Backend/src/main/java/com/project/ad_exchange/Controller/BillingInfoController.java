package com.project.ad_exchange.Controller;

import com.project.ad_exchange.Dto.BillingInfoDto;
import com.project.ad_exchange.Service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingInfoController {

    @Autowired
    private BillingInfoService billingInfoService;

    /**
     * Add or update card info for a user.
     */
    @PostMapping("/{userId}/card")
    public ResponseEntity<?> addOrUpdateCardInfo(@PathVariable Long userId, @RequestBody BillingInfoDto billingInfoDto) {
        try {
            billingInfoService.addOrUpdateCardInfo(userId, billingInfoDto);
            return ResponseEntity.ok("Card information updated successfully for user ID: " + userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }


    /**
     * Get card info for a user.
     */
    @GetMapping("/{userId}/card")
    public ResponseEntity<BillingInfoDto> getCardInfo(@PathVariable Long userId) {
        BillingInfoDto cardInfo = billingInfoService.getCardInfo(userId);
        return ResponseEntity.ok(cardInfo);
    }

    /**
     * Clear card info for a user (remove card details while keeping other billing info).
     */
    @DeleteMapping("/{userId}/card")
    public ResponseEntity<?> clearCardInfo(@PathVariable Long userId) {
        billingInfoService.clearCardInfo(userId);
        return ResponseEntity.ok("Card information cleared successfully for user ID: " + userId);
    }
}
