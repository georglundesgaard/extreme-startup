package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class AdditionQuestionFactory extends BinaryMathsQuestionFactory {
    public AdditionQuestionFactory(Randomizer randomizer) {
        super(randomizer, ADDITION, "what is %d plus %d", Integer::sum);
    }
}
