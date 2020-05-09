package no.lundesgaard.startup.extreme.question.service.factory;

import java.util.function.BiFunction;

import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

public abstract class BinaryMathsQuestionFactory extends QuestionFactory {
    private final BiFunction<Integer, Integer, String> textFunction;
    private final BiFunction<Integer, Integer, String> answerFunction;

    public BinaryMathsQuestionFactory(Randomizer randomizer,
                                      QuestionType questionType,
                                      int points,
                                      BiFunction<Integer, Integer, String> textFunction,
                                      BiFunction<Integer, Integer, String> answerFunction) {
        super(randomizer, questionType, points);
        this.textFunction = textFunction;
        this.answerFunction = answerFunction;
    }

    public BinaryMathsQuestionFactory(Randomizer randomizer,
                                      QuestionType questionType,
                                      BiFunction<Integer, Integer, String> textFunction,
                                      BiFunction<Integer, Integer, String> answerFunction) {
        super(randomizer, questionType);
        this.textFunction = textFunction;
        this.answerFunction = answerFunction;
    }

    @Override
    protected TextAndAnswer textAndAnswer() {
        int n1 = randomizer.nextInt(20);
        int n2 = randomizer.nextInt(20);
        return new TextAndAnswer(textFunction.apply(n1, n2), answerFunction.apply(n1, n2));
    }
}
