package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_ADDITION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class AdditionAdditionQuestionFactory extends TernaryMathsQuestionFactory {
    public AdditionAdditionQuestionFactory(Randomizer randomizer) {
        super(randomizer,
                ADDITION_ADDITION,
                30,
                (n1, n2, n3) -> format("what is %d plus %d plus %d", n1, n2, n3),
                (n1, n2, n3) -> String.valueOf(n1 + n2 + n3));
    }
}
