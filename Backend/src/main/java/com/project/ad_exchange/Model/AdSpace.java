package com.project.ad_exchange.Model;

import jakarta.persistence.*;
@Entity
public class AdSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "website_id", referencedColumnName = "id", nullable = false)
    private Website website;
}
