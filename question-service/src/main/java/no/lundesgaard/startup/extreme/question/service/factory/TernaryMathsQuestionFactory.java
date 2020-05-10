package no.lundesgaard.startup.extreme.question.service.factory;

import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;
import no.lundesgaard.startup.extreme.question.service.function.TriFunction;

public abstract class TernaryMathsQuestionFactory extends QuestionFactory {
    private final TriFunction<Integer, Integer, Integer, String> textFunction;
    private final TriFunction<Integer, Integer, Integer, String> answerFunction;

    public TernaryMathsQuestionFactory(Randomizer randomizer,
                                       QuestionType questionType,
                                       int points,
                                       TriFunction<Integer, Integer, Integer, String> textFunction,
                                       TriFunction<Integer, Integer, Integer, String> answerFunction) {
        super(randomizer, questionType, points);
        this.textFunction = textFunction;
        this.answerFunction = answerFunction;
    }

    public TernaryMathsQuestionFactory(Randomizer randomizer,
                                       QuestionType questionType,
                                       TriFunction<Integer, Integer, Integer, String> textFunction,
                                       TriFunction<Integer, Integer, Integer, String> answerFunction) {
        super(randomizer, questionType);
        this.textFunction = textFunction;
        this.answerFunction = answerFunction;
    }

    @Override
    protected TextAndAnswer textAndAnswer() {
        int n1 = randomizer.nextInt(20);
        int n2 = randomizer.nextInt(20);
        int n3 = randomizer.nextInt(20);
        return new TextAndAnswer(textFunction.apply(n1, n2, n3), answerFunction.apply(n1, n2, n3));
    }
}
