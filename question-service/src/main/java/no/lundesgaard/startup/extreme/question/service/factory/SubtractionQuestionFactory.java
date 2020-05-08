package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class SubtractionQuestionFactory extends BinaryMathsQuestionFactory {
    public SubtractionQuestionFactory(Randomizer randomizer) {
        super(randomizer, SUBTRACTION, "what is %d minus %d", (n1, n2) -> String.valueOf(n1 - n2));
    }
}
