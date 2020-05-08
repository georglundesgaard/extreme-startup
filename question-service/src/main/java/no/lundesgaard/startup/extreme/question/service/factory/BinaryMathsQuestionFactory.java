package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;

import java.util.function.BiFunction;

import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

public abstract class BinaryMathsQuestionFactory extends QuestionFactory {
    private final String textFormat;
    private final BiFunction<Integer, Integer, String> answerFunction;

    public BinaryMathsQuestionFactory(Randomizer randomizer, QuestionType questionType, int points, String textFormat, BiFunction<Integer, Integer, String> answerFunction) {
        super(randomizer, questionType, points);
        this.textFormat = textFormat;
        this.answerFunction = answerFunction;
    }

    public BinaryMathsQuestionFactory(Randomizer randomizer, QuestionType questionType, String textFormat, BiFunction<Integer, Integer, String> answerFunction) {
        super(randomizer, questionType);
        this.textFormat = textFormat;
        this.answerFunction = answerFunction;
    }

    @Override
    protected TextAndAnswer textAndAnswer() {
        int n1 = randomizer.nextInt(20);
        int n2 = randomizer.nextInt(20);
        return new TextAndAnswer(format(textFormat, n1, n2), answerFunction.apply(n1, n2));
    }
}
