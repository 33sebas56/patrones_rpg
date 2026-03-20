package com.sebas.rpg.factory;

import com.sebas.rpg.model.enumtype.ChoiceType;
import com.sebas.rpg.model.enumtype.EventType;
import com.sebas.rpg.model.event.RandomEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomEventFactory {

    private final Random random = new Random();

    public RandomEvent createRandomEvent() {
        EventType[] values = EventType.values();
        EventType selected = values[random.nextInt(values.length)];

        return switch (selected) {
            case STUNNING_STRANGER -> new RandomEvent(
                    EventType.STUNNING_STRANGER,
                    "Figura deslumbrante detectada",
                    "Ves una figura absurdamente atractiva a lo lejos. Tu autoestima se prepara para una humillación estadísticamente probable.",
                    List.of(ChoiceType.APPROACH, ChoiceType.KEEP_WALKING)
            );
            case FREE_FOOD_TRAP -> new RandomEvent(
                    EventType.FREE_FOOD_TRAP,
                    "Comida gratis. Qué podría salir mal",
                    "Encuentras comida gratis en un lugar sospechosamente conveniente. La supervivencia y la codicia empiezan a discutir.",
                    List.of(ChoiceType.EAT_IT, ChoiceType.IGNORE_IT)
            );
            case MYSTERIOUS_LINK -> new RandomEvent(
                    EventType.MYSTERIOUS_LINK,
                    "Mensaje confiable de una fuente nada confiable",
                    "Recibes un link con el texto: 'bro trust me'. La historia demuestra que jamás debiste abrir eso.",
                    List.of(ChoiceType.OPEN_IT, ChoiceType.DELETE_IT)
            );
            case ACADEMIC_FLASHBACK -> new RandomEvent(
                    EventType.ACADEMIC_FLASHBACK,
                    "Recuerdo traumático académico",
                    "Te acuerdas de una entrega que quizá existía. O quizá no. Pero tu ansiedad ya dijo que sí.",
                    List.of(ChoiceType.CHECK_IT, ChoiceType.DENY_REALITY)
            );
        };
    }
}