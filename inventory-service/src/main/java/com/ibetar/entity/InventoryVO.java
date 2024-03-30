package com.ibetar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryVO {
    private String profileUUID;
    private String upcCode;
    private int quantity;
}
