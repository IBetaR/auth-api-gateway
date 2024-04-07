package com.ibetar.controller;

import com.ibetar.entity.InventoryVO;
import com.ibetar.service.InventoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
@AllArgsConstructor
public class InventoryController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final InventoryService inventoryService;

    @GetMapping("profile/{uuid}")
    public ResponseEntity<InventoryVO> getInventory(@PathVariable("uuid") String uuid) {
        LOGGER.info("Retrieving profile's inventory with uuid: " + uuid);
        return ResponseEntity.ok(inventoryService.getInventory(uuid));
    }

    // Refactor to Put mapping
    @PostMapping("profiles/update/{uuid}")
    public ResponseEntity<String> updateInventory() {
        LOGGER.info("Updating profile Inventory");
        return ResponseEntity.ok("Should Update profile's Inventory");
    }

}