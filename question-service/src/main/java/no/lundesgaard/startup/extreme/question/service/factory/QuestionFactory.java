package no.lundesgaard.startup.extreme.question.service.factory;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public abstract class QuestionFactory {
    protected final Randomizer randomizer;
    private final QuestionType questionType;
    private int points = 10;
    
    public Question generateRandomQuestion() {
        String id = randomizer.nextQuestionId();
        TextAndAnswer textAndAnswer = textAndAnswer();
        return new Question(id, questionType, points, textAndAnswer.text(), textAndAnswer.answer());
    }

    protected abstract TextAndAnswer textAndAnswer();
}

record TextAndAnswer(String text, String answer) {}
