package no.lundesgaard.startup.extreme.question.service;

import java.util.List;

import no.lundesgaard.startup.extreme.question.service.factory.QuestionFactory;
import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final List<QuestionFactory> questionFactories;
    private final Randomizer randomizer;

    public QuestionService(List<QuestionFactory> questionFactories, Randomizer randomizer) {
        this.questionFactories = questionFactories;
        this.randomizer = randomizer;
    }

    public Question generateRandomQuestion(int round) {
        QuestionType type = randomizer.nextQuestionType(round);
        return questionFactories.stream()
                .filter(factory -> type.equals(factory.getQuestionType()))
                .findFirst()
                .orElseThrow()
                .generateRandomQuestion();
    }

    public Question createWarmupQuestion(String player) {
        String id = randomizer.nextQuestionId();
        return new Question(id, QuestionType.WARMUP, 10, "what is your name", player);
    }
}
