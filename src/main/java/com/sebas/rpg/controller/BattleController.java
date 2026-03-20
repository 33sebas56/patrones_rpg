package com.sebas.rpg.controller;

import com.sebas.rpg.dto.request.BattleRequest;
import com.sebas.rpg.dto.response.BattleResultResponse;
import com.sebas.rpg.service.BattleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battles")
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<BattleResultResponse> simulateBattle(@RequestBody BattleRequest request) {
        BattleResultResponse response = battleService.simulateBattle(request.getEnemyName());
        return ResponseEntity.ok(response);
    }
}