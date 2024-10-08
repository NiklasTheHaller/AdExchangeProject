package com.project.ad_exchange.Model;

import jakarta.persistence.*;
@Entity
public class BillingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Street;
    private String postalCode;
    private String city;
    private String country;
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;
    private String cardNumber; // Store only the last 4 digits for security
    private String cardHolderName;
    private String expirationDate; // MM/YYYY
   @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user; // Link to the user who owns this billing info


}
