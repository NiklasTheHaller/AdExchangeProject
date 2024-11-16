package com.project.ad_exchange.Repository;

import com.project.ad_exchange.Model.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
    Optional<BillingInfo> findByUserId(Long userId);
}
