package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION_ADDITION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class MultiplicationAdditionQuestionFactory extends TernaryMathsQuestionFactory {
    public MultiplicationAdditionQuestionFactory(Randomizer randomizer) {
        super(randomizer,
                MULTIPLICATION_ADDITION,
                50,
                (n1, n2, n3) -> format("what is %d multiplied by %d plus %d", n1, n2, n3),
                (n1, n2, n3) -> String.valueOf(n1 * n2 + n3));
    }
}
