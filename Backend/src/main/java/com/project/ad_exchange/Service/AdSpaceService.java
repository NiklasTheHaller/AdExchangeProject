package com.project.ad_exchange.Service;

import com.project.ad_exchange.Dto.AdSpaceDto;
import com.project.ad_exchange.Model.AdSpace;
import com.project.ad_exchange.Model.Website;
import com.project.ad_exchange.Repository.AdSpaceRepository;
import com.project.ad_exchange.Repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdSpaceService {

    private final AdSpaceRepository adSpaceRepository;
    private final WebsiteRepository websiteRepository;

    @Autowired
    public AdSpaceService(AdSpaceRepository adSpaceRepository, WebsiteRepository websiteRepository) {
        this.adSpaceRepository = adSpaceRepository;
        this.websiteRepository = websiteRepository;
    }

    /**
     * Create a new AdSpace from the AdSpaceDto and save it to the database.
     *
     * @param adSpaceDto The DTO containing the ad space details.
     * @return The saved AdSpace entity.
     */
    public AdSpace createAdSpace(AdSpaceDto adSpaceDto) {
        // Retrieve the associated Website entity
        Website website = websiteRepository.findById(adSpaceDto.websiteId())
                .orElseThrow(() -> new IllegalArgumentException("Website not found for ID: " + adSpaceDto.websiteId()));

        // Map AdSpaceDto to AdSpace entity
        AdSpace adSpace = new AdSpace();
        adSpace.setWebsite(website);
        adSpace.setKeywords(adSpaceDto.keywords());
        adSpace.setWidth(adSpaceDto.width());
        adSpace.setHeight(adSpaceDto.height());
        adSpace.setPosition(adSpaceDto.position());
        adSpace.setAdTypes(adSpaceDto.adTypes());
        adSpace.setMinCpm(adSpaceDto.minCpm());
        adSpace.setFloorPrice(adSpaceDto.floorPrice());
        adSpace.setAllowedAdvertisers(adSpaceDto.allowedAdvertisers());
        adSpace.setBlockedAdvertisers(adSpaceDto.blockedAdvertisers());
        adSpace.setAllowedCategories(adSpaceDto.allowedCategories());
        adSpace.setBlockedCategories(adSpaceDto.blockedCategories());
        adSpace.setViewability(adSpaceDto.viewability());
        adSpace.setDeviceTypes(adSpaceDto.deviceTypes());
        adSpace.setGeoTargeting(adSpaceDto.geoTargeting());
        adSpace.setLanguage(adSpaceDto.language());
        adSpace.setSecure(adSpaceDto.secure());

        // Save the AdSpace entity to the database
        return adSpaceRepository.save(adSpace);
    }

    /**
     * Retrieve all AdSpace entities from the database.
     *
     * @return A list of AdSpace entities.
     */
    public List<AdSpace> getAllAdSpaces() {
        return adSpaceRepository.findAll();
    }

    /**
     * Retrieve a specific AdSpace entity by its ID.
     *
     * @param id The ID of the AdSpace to retrieve.
     * @return The AdSpace entity if found.
     */
    public AdSpace getAdSpaceById(Long id) {
        return adSpaceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("AdSpace not found for ID: " + id));
    }

}
