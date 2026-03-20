package com.sebas.rpg.dto.request;

import com.sebas.rpg.model.enumtype.ItemType;

public class EquipItemRequest {

    private ItemType itemType;

    public EquipItemRequest() {
    }

    public EquipItemRequest(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}