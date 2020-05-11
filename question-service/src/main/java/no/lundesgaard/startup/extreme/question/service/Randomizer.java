package no.lundesgaard.startup.extreme.question.service;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MAXIMUM;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION_ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.springframework.stereotype.Component;

@Component
public class Randomizer {
    private static final QuestionType[] QUESTION_TYPES = {
            ADDITION,
            SUBTRACTION,
            MULTIPLICATION,
            POWER,
            FIBONACCI,
            ADDITION_ADDITION,
            ADDITION_MULTIPLICATION,
            MULTIPLICATION_ADDITION,
            MAXIMUM
    };

    public int nextInt(int bound) {
        return new Random().nextInt(bound);
    }

    public String nextQuestionId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public QuestionType nextQuestionType(int round) {
        return QUESTION_TYPES[nextInt(QUESTION_TYPES.length)];
    }

    public int[] initNumbers(int[] candidateNumbers) {
        int size = nextInt(2) + 1;
        int[] numbers = Stream.of(
                Stream.generate(() -> nextInt(1000)).limit(size),
                stream(shuffle(candidateNumbers)).boxed().limit(size)
        ).flatMap(identity())
                .mapToInt(Integer::intValue)
                .toArray();
        return shuffle(numbers);
    }

    public int[] shuffle(int[] numbers) {
        List<Integer> numberList = Arrays.stream(numbers).boxed().collect(toList());
        Collections.shuffle(numberList);
        return numberList.stream().mapToInt(Integer::intValue).toArray();
    }
}
