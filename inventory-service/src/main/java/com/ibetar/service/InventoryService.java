package com.ibetar.service;

import com.ibetar.entity.InventoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    public InventoryVO getInventory(String uuid) {
        LOGGER.info("Consulting inventory for profile with uuid: " + uuid);

        InventoryVO mockedInventory = new InventoryVO();
        mockedInventory.setProfileUUID(uuid);
        mockedInventory.setQuantity(1);
        mockedInventory.setUpcCode("MOCKED_ITEM");

        LOGGER.info("Returning inventory: " + mockedInventory);
        return mockedInventory;
    }
}
