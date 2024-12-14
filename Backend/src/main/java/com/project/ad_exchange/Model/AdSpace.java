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

    @Column(name = "keywords", nullable = true)
    private String keywords; // Comma-separated keywords

    @Column(name = "width", nullable = false)
    private int width = 0;

    @Column(name = "height", nullable = false)
    private int height = 0;

    @Column(name = "position", nullable = true)
    private String position; // Position description

    @Column(name = "ad_types", nullable = false)
    private String adTypes; // Comma-separated ad types (e.g., banner, video, native)

    @Column(name = "min_cpm", nullable = false)
    private double minCpm = 0.0;

    @Column(name = "floor_price", nullable = false)
    private double floorPrice = 0.0;

    @Column(name = "allowed_advertisers", nullable = true)
    private String allowedAdvertisers; // Comma-separated list of advertisers

    @Column(name = "blocked_advertisers", nullable = true)
    private String blockedAdvertisers; // Comma-separated list of advertisers

    @Column(name = "allowed_categories", nullable = true)
    private String allowedCategories; // Comma-separated list of categories

    @Column(name = "blocked_categories", nullable = true)
    private String blockedCategories; // Comma-separated list of categories

    @Column(name = "viewability", nullable = false)
    private double viewability = 0.0; // Percentage (e.g., 50.0)

    @Column(name = "device_types", nullable = false)
    private String deviceTypes; // Comma-separated list of device types (desktop, mobile, tablet)

    @Column(name = "geo_targeting", nullable = true)
    private String geoTargeting; // Comma-separated geo targeting codes

    @Column(name = "language", nullable = true)
    private String language; // Language code (ISO format)

    @Column(name = "secure", nullable = false)
    private boolean secure = false; // Boolean flag for secure ads

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAdTypes() {
        return adTypes;
    }

    public void setAdTypes(String adTypes) {
        this.adTypes = adTypes;
    }

    public double getMinCpm() {
        return minCpm;
    }

    public void setMinCpm(double minCpm) {
        this.minCpm = minCpm;
    }

    public double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(double floorPrice) {
        this.floorPrice = floorPrice;
    }

    public String getAllowedAdvertisers() {
        return allowedAdvertisers;
    }

    public void setAllowedAdvertisers(String allowedAdvertisers) {
        this.allowedAdvertisers = allowedAdvertisers;
    }

    public String getBlockedAdvertisers() {
        return blockedAdvertisers;
    }

    public void setBlockedAdvertisers(String blockedAdvertisers) {
        this.blockedAdvertisers = blockedAdvertisers;
    }

    public String getAllowedCategories() {
        return allowedCategories;
    }

    public void setAllowedCategories(String allowedCategories) {
        this.allowedCategories = allowedCategories;
    }

    public String getBlockedCategories() {
        return blockedCategories;
    }

    public void setBlockedCategories(String blockedCategories) {
        this.blockedCategories = blockedCategories;
    }

    public double getViewability() {
        return viewability;
    }

    public void setViewability(double viewability) {
        this.viewability = viewability;
    }

    public String getDeviceTypes() {
        return deviceTypes;
    }

    public void setDeviceTypes(String deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public String getGeoTargeting() {
        return geoTargeting;
    }

    public void setGeoTargeting(String geoTargeting) {
        this.geoTargeting = geoTargeting;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
