package com.project.ad_exchange.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;

    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdSpace> adSpaces = new ArrayList<>();
    private Category category;

}
