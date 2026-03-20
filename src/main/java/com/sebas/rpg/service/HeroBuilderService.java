package com.sebas.rpg.service;

import com.sebas.rpg.dto.response.HeroResponse;
import com.sebas.rpg.exception.HeroNotFoundException;
import com.sebas.rpg.factory.HeroFactory;
import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.buff.BrokenHeartDebuffDecorator;
import com.sebas.rpg.model.buff.CoffeeRushBuffDecorator;
import com.sebas.rpg.model.buff.ConfusionDebuffDecorator;
import com.sebas.rpg.model.buff.ExistentialCrisisDebuffDecorator;
import com.sebas.rpg.model.buff.MainCharacterBuffDecorator;
import com.sebas.rpg.model.buff.MomBlessingBuffDecorator;
import com.sebas.rpg.model.buff.NightShiftBuffDecorator;
import com.sebas.rpg.model.decorator.BudgetShieldDecorator;
import com.sebas.rpg.model.decorator.CardboardArmorDecorator;
import com.sebas.rpg.model.decorator.IronArmorDecorator;
import com.sebas.rpg.model.decorator.LegendarySandalDecorator;
import com.sebas.rpg.model.decorator.RustySwordDecorator;
import com.sebas.rpg.model.decorator.SuspiciousAmuletDecorator;
import com.sebas.rpg.model.enumtype.BuffType;
import com.sebas.rpg.model.enumtype.HeroClassType;
import com.sebas.rpg.model.enumtype.ItemType;
import org.springframework.stereotype.Service;

@Service
public class HeroBuilderService {

    private final HeroFactory heroFactory;
    private Hero currentHero;

    public HeroBuilderService(HeroFactory heroFactory) {
        this.heroFactory = heroFactory;
    }

    public HeroResponse createHero(String name, HeroClassType heroClassType) {
        currentHero = heroFactory.createHero(name, heroClassType);
        return toResponse(currentHero);
    }

    public HeroResponse equipItem(ItemType itemType) {
        Hero hero = getCurrentHeroOrThrow();

        currentHero = switch (itemType) {
            case IRON_ARMOR -> new IronArmorDecorator(hero);
            case CARDBOARD_ARMOR -> new CardboardArmorDecorator(hero);
            case LEGENDARY_SANDAL -> new LegendarySandalDecorator(hero);
            case RUSTY_SWORD -> new RustySwordDecorator(hero);
            case BUDGET_SHIELD -> new BudgetShieldDecorator(hero);
            case SUSPICIOUS_AMULET -> new SuspiciousAmuletDecorator(hero);
        };

        return toResponse(currentHero);
    }

    public HeroResponse applyBuff(BuffType buffType) {
        Hero hero = getCurrentHeroOrThrow();

        currentHero = switch (buffType) {
            case COFFEE_RUSH -> new CoffeeRushBuffDecorator(hero);
            case MOM_BLESSING -> new MomBlessingBuffDecorator(hero);
            case MAIN_CHARACTER -> new MainCharacterBuffDecorator(hero);
            case NIGHT_SHIFT -> new NightShiftBuffDecorator(hero);
            case CONFUSION -> new ConfusionDebuffDecorator(hero);
            case BROKEN_HEART -> new BrokenHeartDebuffDecorator(hero);
            case EXISTENTIAL_CRISIS -> new ExistentialCrisisDebuffDecorator(hero);
        };

        return toResponse(currentHero);
    }

    public HeroResponse getCurrentHero() {
        Hero hero = getCurrentHeroOrThrow();
        return toResponse(hero);
    }

    public Hero getCurrentHeroEntity() {
        return getCurrentHeroOrThrow();
    }

    private Hero getCurrentHeroOrThrow() {
        if (currentHero == null) {
            throw new HeroNotFoundException("No hero has been created yet");
        }
        return currentHero;
    }

    private HeroResponse toResponse(Hero hero) {
        return new HeroResponse(
                hero.getName(),
                hero.getDescription(),
                hero.getHealth(),
                hero.getAttack(),
                hero.getDefense(),
                hero.getSpeed(),
                hero.getCritChance(),
                hero.getLuck()
        );
    }
}