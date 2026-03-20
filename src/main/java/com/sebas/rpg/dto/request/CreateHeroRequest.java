package com.sebas.rpg.dto.request;

import com.sebas.rpg.model.enumtype.HeroClassType;

public class CreateHeroRequest {

    private String name;
    private HeroClassType heroClassType;

    public CreateHeroRequest() {
    }

    public CreateHeroRequest(String name, HeroClassType heroClassType) {
        this.name = name;
        this.heroClassType = heroClassType;
    }

    public String getName() {
        return name;
    }

    public HeroClassType getHeroClassType() {
        return heroClassType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeroClassType(HeroClassType heroClassType) {
        this.heroClassType = heroClassType;
    }
}