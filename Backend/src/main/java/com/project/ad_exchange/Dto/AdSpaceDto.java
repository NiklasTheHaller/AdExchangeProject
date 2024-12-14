package com.project.ad_exchange.Dto;

public record AdSpaceDto(
        String token,
        String keywords, // Comma-separated keywords
        int width,
        int height,
        String position, // Position description
        String adTypes, // Comma-separated ad types (e.g., banner, video, native)
        double minCpm, // Minimum cost per mille
        double floorPrice, // Minimum bid price
        String allowedAdvertisers, // Comma-separated list of allowed advertisers
        String blockedAdvertisers, // Comma-separated list of blocked advertisers
        String allowedCategories, // Comma-separated list of allowed categories
        String blockedCategories, // Comma-separated list of blocked categories
        double viewability, // Percentage (e.g., 50.0)
        String deviceTypes, // Comma-separated list of device types (desktop, mobile, tablet)
        String geoTargeting, // Comma-separated geo targeting codes
        String language, // Language code (ISO format)
        boolean secure, // Boolean flag for secure ads
        long websiteId // ID of the related website
) {}
