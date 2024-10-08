package com.project.ad_exchange.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "ad_space_id", referencedColumnName = "id", nullable = false)
    private AdSpace adSpace;

    @ManyToOne
    @JoinColumn(name = "advertiser_id", referencedColumnName = "id", nullable = false)
    private User advertiser;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    private User publisher;

}
