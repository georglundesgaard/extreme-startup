package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class AdditionQuestionFactory extends BinaryMathsQuestionFactory {
    public AdditionQuestionFactory(Randomizer randomizer) {
        super(randomizer, ADDITION, (n1, n2) -> format("what is %d plus %d", n1, n2), (n1, n2) -> String.valueOf(n1 + n2));
    }
}
