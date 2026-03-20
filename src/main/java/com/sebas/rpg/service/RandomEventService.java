package com.sebas.rpg.service;

import com.sebas.rpg.dto.response.EventOutcomeResponse;
import com.sebas.rpg.dto.response.RandomEventResponse;
import com.sebas.rpg.exception.InvalidChoiceException;
import com.sebas.rpg.factory.RandomEventFactory;
import com.sebas.rpg.model.enumtype.BuffType;
import com.sebas.rpg.model.enumtype.ChoiceType;
import com.sebas.rpg.model.enumtype.EventType;
import com.sebas.rpg.model.event.EventOutcome;
import com.sebas.rpg.model.event.RandomEvent;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomEventService {

    private final RandomEventFactory randomEventFactory;
    private final HeroBuilderService heroBuilderService;
    private final Random random;

    public RandomEventService(RandomEventFactory randomEventFactory, HeroBuilderService heroBuilderService) {
        this.randomEventFactory = randomEventFactory;
        this.heroBuilderService = heroBuilderService;
        this.random = new Random();
    }

    public RandomEventResponse getRandomEvent() {
        RandomEvent event = randomEventFactory.createRandomEvent();

        return new RandomEventResponse(
                event.getEventType(),
                event.getTitle(),
                event.getDescription(),
                event.getAvailableChoices()
        );
    }

    public EventOutcomeResponse resolveEvent(EventType eventType, ChoiceType choiceType) {
        EventOutcome outcome = switch (eventType) {
            case STUNNING_STRANGER -> resolveStunningStranger(choiceType);
            case FREE_FOOD_TRAP -> resolveFreeFoodTrap(choiceType);
            case MYSTERIOUS_LINK -> resolveMysteriousLink(choiceType);
            case ACADEMIC_FLASHBACK -> resolveAcademicFlashback(choiceType);
        };

        heroBuilderService.applyEffect(outcome.getAppliedEffect());

        return new EventOutcomeResponse(
                outcome.getTitle(),
                outcome.getDescription(),
                outcome.getAppliedEffect().name()
        );
    }

    private EventOutcome resolveStunningStranger(ChoiceType choiceType) {
        validateChoice(choiceType, ChoiceType.APPROACH, ChoiceType.KEEP_WALKING);

        if (choiceType == ChoiceType.APPROACH) {
            int roll = random.nextInt(100) + 1;

            if (roll <= 25) {
                return new EventOutcome(
                        "Rechazo elegante",
                        "Te acercaste con confianza prestada y fuiste ignorado con una precisión quirúrgica. Tu siguiente combate tendrá vibes de corazón roto.",
                        BuffType.BROKEN_HEART
                );
            } else if (roll <= 50) {
                return new EventOutcome(
                        "Era un enemigo con excelente skin",
                        "Resultó ser una entidad hostil con demasiada presencia escénica. Quedaste confundido antes del próximo combate. Gran estrategia, campeón.",
                        BuffType.CONFUSION
                );
            } else if (roll <= 75) {
                return new EventOutcome(
                        "Conversación sorprendentemente decente",
                        "Contra toda expectativa, la interacción salió bien. Tu ego ahora cree que protagonizas una saga.",
                        BuffType.MAIN_CHARACTER
                );
            } else {
                return new EventOutcome(
                        "Crisis existencial inmediata",
                        "No era amor, era una proyección de tus decisiones dudosas. Te quedaste pensando demasiado y claramente eso no ayuda en combate.",
                        BuffType.EXISTENTIAL_CRISIS
                );
            }
        }

        return new EventOutcome(
                "Decisión madura, increíblemente sospechosa",
                "Seguiste caminando. Por primera vez en tu vida elegiste la paz sobre el caos. Tu estabilidad mental mejora levemente.",
                BuffType.MOM_BLESSING
        );
    }

    private EventOutcome resolveFreeFoodTrap(ChoiceType choiceType) {
        validateChoice(choiceType, ChoiceType.EAT_IT, ChoiceType.IGNORE_IT);

        if (choiceType == ChoiceType.EAT_IT) {
            int roll = random.nextInt(100) + 1;

            if (roll <= 35) {
                return new EventOutcome(
                        "Azúcar, cafeína y decisiones terribles",
                        "La comida tenía ingredientes ilegales en tres continentes. Ahora te mueves más rápido de lo recomendable.",
                        BuffType.COFFEE_RUSH
                );
            } else if (roll <= 70) {
                return new EventOutcome(
                        "Comida emocionalmente devastadora",
                        "Sabía increíble, pero ahora sospechas demasiado de la vida. Obtienes confusión. Delicioso.",
                        BuffType.CONFUSION
                );
            } else {
                return new EventOutcome(
                        "Experiencia culinaria mística",
                        "No sabes qué comiste, pero tu cuerpo decidió que era cinema. Obtienes energía de protagonista.",
                        BuffType.MAIN_CHARACTER
                );
            }
        }

        return new EventOutcome(
                "Paranoia funcional",
                "Ignoraste la comida gratis. Esa desconfianza social probablemente te salvó. Ligeramente bendecido por el sentido común.",
                BuffType.MOM_BLESSING
        );
    }

    private EventOutcome resolveMysteriousLink(ChoiceType choiceType) {
        validateChoice(choiceType, ChoiceType.OPEN_IT, ChoiceType.DELETE_IT);

        if (choiceType == ChoiceType.OPEN_IT) {
            int roll = random.nextInt(100) + 1;

            if (roll <= 30) {
                return new EventOutcome(
                        "Malware espiritual",
                        "Abriste el link. Ahora no solo el dispositivo está en riesgo, también tu dignidad. Quedas existencialmente dañado.",
                        BuffType.EXISTENTIAL_CRISIS
                );
            } else if (roll <= 60) {
                return new EventOutcome(
                        "Tutorial prohibido",
                        "Encontraste algo útil de manera estadísticamente imposible. Ganas energía de noche sin dormir.",
                        BuffType.NIGHT_SHIFT
                );
            } else {
                return new EventOutcome(
                        "Fake guru arc",
                        "Caíste en un discurso tan absurdo que ahora, por algún motivo, te sientes el protagonista. Qué peligro.",
                        BuffType.MAIN_CHARACTER
                );
            }
        }

        return new EventOutcome(
                "Milagro digital",
                "Borraste el link sin abrirlo. Eso demuestra crecimiento personal o miedo. En ambos casos, te hizo bien.",
                BuffType.MOM_BLESSING
        );
    }

    private EventOutcome resolveAcademicFlashback(ChoiceType choiceType) {
        validateChoice(choiceType, ChoiceType.CHECK_IT, ChoiceType.DENY_REALITY);

        if (choiceType == ChoiceType.CHECK_IT) {
            int roll = random.nextInt(100) + 1;

            if (roll <= 40) {
                return new EventOutcome(
                        "Pánico productivo",
                        "Sí había algo pendiente. Tu cuerpo liberó una mezcla tóxica de estrés y enfoque. No es sano, pero funciona.",
                        BuffType.NIGHT_SHIFT
                );
            } else if (roll <= 75) {
                return new EventOutcome(
                        "Nada pendiente, daño igual",
                        "No había entrega. Igual ya te arruinaste la paz mental. Felicidades por sufrir gratis.",
                        BuffType.EXISTENTIAL_CRISIS
                );
            } else {
                return new EventOutcome(
                        "Redención académica",
                        "Encontraste orden dentro del caos. Por un instante fugaz, pareces una persona funcional.",
                        BuffType.MOM_BLESSING
                );
            }
        }

        return new EventOutcome(
                "Negación táctica",
                "Elegiste no revisar nada. Técnicamente sigues en peligro, pero emocionalmente te sientes invencible.",
                BuffType.MAIN_CHARACTER
        );
    }

    private void validateChoice(ChoiceType actual, ChoiceType firstValid, ChoiceType secondValid) {
        if (actual != firstValid && actual != secondValid) {
            throw new InvalidChoiceException("Invalid choice for the selected event");
        }
    }
}