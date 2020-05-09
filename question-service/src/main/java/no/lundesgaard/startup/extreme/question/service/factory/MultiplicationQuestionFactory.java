package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class MultiplicationQuestionFactory extends BinaryMathsQuestionFactory {
    public MultiplicationQuestionFactory(Randomizer randomizer) {
        super(randomizer, MULTIPLICATION, (n1, n2) -> format("what is %d multiplied by %d", n1, n2), (n1, n2) -> String.valueOf(n1 * n2));
    }
}
