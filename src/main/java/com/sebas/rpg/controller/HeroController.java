package com.sebas.rpg.controller;

import com.sebas.rpg.dto.request.ApplyBuffRequest;
import com.sebas.rpg.dto.request.CreateHeroRequest;
import com.sebas.rpg.dto.request.EquipItemRequest;
import com.sebas.rpg.dto.response.HeroResponse;
import com.sebas.rpg.service.HeroBuilderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroBuilderService heroBuilderService;

    public HeroController(HeroBuilderService heroBuilderService) {
        this.heroBuilderService = heroBuilderService;
    }

    @PostMapping
    public ResponseEntity<HeroResponse> createHero(@RequestBody CreateHeroRequest request) {
        HeroResponse response = heroBuilderService.createHero(request.getName(), request.getHeroClassType());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/equip")
    public ResponseEntity<HeroResponse> equipItem(@RequestBody EquipItemRequest request) {
        HeroResponse response = heroBuilderService.equipItem(request.getItemType());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/buff")
    public ResponseEntity<HeroResponse> applyBuff(@RequestBody ApplyBuffRequest request) {
        HeroResponse response = heroBuilderService.applyBuff(request.getBuffType());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current")
    public ResponseEntity<HeroResponse> getCurrentHero() {
        return ResponseEntity.ok(heroBuilderService.getCurrentHero());
    }
}