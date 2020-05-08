package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class PowerQuestionFactory extends BinaryMathsQuestionFactory {
    public PowerQuestionFactory(Randomizer randomizer) {
        super(randomizer, POWER, 20, "what is %d to the power of %d", (n1, n2) -> String.valueOf((long) Math.pow(n1, n2)));
    }
}
