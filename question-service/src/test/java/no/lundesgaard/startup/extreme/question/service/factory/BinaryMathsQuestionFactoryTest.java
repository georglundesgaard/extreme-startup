package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BinaryMathsQuestionFactoryTest {
    @MockBean
    private Randomizer randomizer;
    
    private final QuestionType questionType;
    private int points = 10;

    public BinaryMathsQuestionFactoryTest(QuestionType questionType, int points) {
        this.questionType = questionType;
        this.points = points;
    }

    @Test
    @DisplayName("return correct question type")
    void getQuestionType() {
        assertThat(factory().getQuestionType()).isEqualTo(questionType);
    }

    @ParameterizedTest
    @MethodSource("questions")
    @DisplayName("return correct random question")
    void generateRandomQuestion(String id, int n1, int n2, String expectedText, String expectedAnswer) {
        when(randomizer.nextQuestionId()).thenReturn(id);
        when(randomizer.nextInt(20)).thenReturn(n1, n2);
        Question result = factory().generateRandomQuestion();
        assertAll("question", 
                () -> assertThat(result).where().id().isEqualTo(id),
                () -> assertThat(result).where().type().isEqualTo(questionType),
                () -> assertThat(result).where().points().isEqualTo(points),
                () -> assertThat(result).where().text().isEqualTo(expectedText),
                () -> assertThat(result).where().answer().isEqualTo(expectedAnswer));
    }

    protected abstract QuestionFactory factory();
}
