package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MAXIMUM;

import java.util.stream.IntStream;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class MaximumQuestionFactory extends QuestionFactory {
    public MaximumQuestionFactory(Randomizer randomizer) {
        super(randomizer, MAXIMUM, 40);
    }

    @Override
    protected TextAndAnswer textAndAnswer() {
        int[] candidateNumbers = IntStream.iterate(0, n -> n++)
                .limit(100)
                .toArray();
        int[] numbers = randomizer.initNumbers(candidateNumbers);
        String numbersString = stream(numbers)
                .mapToObj(String::valueOf)
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElseThrow();
        int max = stream(numbers).max().orElseThrow();
        return new TextAndAnswer(format("which of the following numbers is the largest: %s", numbersString), String.valueOf(max));
    }
}
