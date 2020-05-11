package no.lundesgaard.startup.extreme.question.service.factory;

import static java.util.Arrays.stream;
import static no.lundesgaard.startup.extreme.question.assertj.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.UUID;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class MathsQuestionFactoryTest extends QuestionFactoryTest {
    
    public MathsQuestionFactoryTest(QuestionType questionType, int points) {
        super(questionType, points);
    }

    public MathsQuestionFactoryTest(QuestionType questionType) {
        super(questionType);
    }

    @ParameterizedTest
    @MethodSource("questions")
    @DisplayName("return correct random question")
    void generateRandomQuestion(int[] numbers, String expectedText, String expectedAnswer) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        when(randomizer.nextQuestionId()).thenReturn(id);
        when(randomizer.nextInt(20)).thenReturn(numbers[0], stream(numbers).skip(1).boxed().toArray(Integer[]::new));
        Question result = factory().generateRandomQuestion();
        assertAll("question", 
                () -> assertThat(result).where().id().isEqualTo(id),
                () -> assertThat(result).where().type().isEqualTo(questionType),
                () -> assertThat(result).where().points().isEqualTo(points),
                () -> assertThat(result).where().text().isEqualTo(expectedText),
                () -> assertThat(result).where().answer().isEqualTo(expectedAnswer));
    }
}
