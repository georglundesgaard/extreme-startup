package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_MULTIPLICATION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class AdditionMultiplicationQuestionFactory extends TernaryMathsQuestionFactory {
    public AdditionMultiplicationQuestionFactory(Randomizer randomizer) {
        super(randomizer,
                ADDITION_MULTIPLICATION,
                60,
                (n1, n2, n3) -> format("what is %d plus %d multiplied by %d", n1, n2, n3),
                (n1, n2, n3) -> String.valueOf(n1 + n2 * n3));
    }
}
