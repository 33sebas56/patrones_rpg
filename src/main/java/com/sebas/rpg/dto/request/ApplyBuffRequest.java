package com.sebas.rpg.dto.request;

import com.sebas.rpg.model.enumtype.BuffType;

public class ApplyBuffRequest {

    private BuffType buffType;

    public ApplyBuffRequest() {
    }

    public ApplyBuffRequest(BuffType buffType) {
        this.buffType = buffType;
    }

    public BuffType getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffType buffType) {
        this.buffType = buffType;
    }
}