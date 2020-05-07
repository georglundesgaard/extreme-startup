package no.lundesgaard.startup.extreme.question.service;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;

import java.util.Random;

import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.springframework.stereotype.Component;

@Component
public class Randomizer {
    public int nextInt(int bound) {
        return new Random().nextInt(bound);
    }

    public String nextQuestionId() {
        return "cafebabe";
    }

    public QuestionType nextQuestionType(int round) {
        return ADDITION;
    }
}
