package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

public class MultiplicationQuestionFactory extends BinaryMathsQuestionFactory {
    public MultiplicationQuestionFactory(Randomizer randomizer) {
        super(randomizer, MULTIPLICATION, "what is %d multiplied by %d", (n1, n2) -> n1 * n2);
    }
}
