package no.lundesgaard.startup.extreme.question.service;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;

import java.util.Random;
import java.util.UUID;

import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.springframework.stereotype.Component;

@Component
public class Randomizer {
    private static final QuestionType[] QUESTION_TYPES = { ADDITION, SUBTRACTION, MULTIPLICATION, POWER, FIBONACCI };

    public int nextInt(int bound) {
        return new Random().nextInt(bound);
    }

    public String nextQuestionId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public QuestionType nextQuestionType(int round) {
        return QUESTION_TYPES[nextInt(QUESTION_TYPES.length)];
    }
}
