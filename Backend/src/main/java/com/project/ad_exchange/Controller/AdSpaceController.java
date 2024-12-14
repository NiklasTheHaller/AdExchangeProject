package com.project.ad_exchange.Controller;

import com.project.ad_exchange.Dto.AdSpaceDto;
import com.project.ad_exchange.Model.AdSpace;
import com.project.ad_exchange.Service.AdSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adspaces")
public class AdSpaceController {

    private final AdSpaceService adSpaceService;

    @Autowired
    public AdSpaceController(AdSpaceService adSpaceService) {
        this.adSpaceService = adSpaceService;
    }

    /**
     * Create a new ad space.
     *
     * @param adSpaceDto The DTO containing the ad space details.
     * @return The created ad space.
     */
    @PostMapping
    public ResponseEntity<AdSpace> createAdSpace(@RequestBody AdSpaceDto adSpaceDto) {
        AdSpace createdAdSpace = adSpaceService.createAdSpace(adSpaceDto);
        return ResponseEntity.ok(createdAdSpace);
    }

    /**
     * Retrieve all ad spaces.
     *
     * @return A list of all ad spaces.
     */
    @GetMapping
    public ResponseEntity<List<AdSpace>> getAllAdSpaces() {
        List<AdSpace> adSpaces = adSpaceService.getAllAdSpaces();
        return ResponseEntity.ok(adSpaces);
    }
}
