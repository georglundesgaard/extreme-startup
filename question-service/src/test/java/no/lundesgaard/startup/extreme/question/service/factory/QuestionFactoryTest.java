package no.lundesgaard.startup.extreme.question.service.factory;

import static org.assertj.core.api.Assertions.assertThat;

import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class QuestionFactoryTest {
    protected final QuestionType questionType;
    protected int points = 10;

    @MockBean
    protected Randomizer randomizer;

    public QuestionFactoryTest(QuestionType questionType, int points) {
        this.questionType = questionType;
        this.points = points;
    }

    @Test
    @DisplayName("return correct question type")
    void getQuestionType() {
        assertThat(factory().getQuestionType()).isEqualTo(questionType);
    }

    protected abstract QuestionFactory factory();
}
