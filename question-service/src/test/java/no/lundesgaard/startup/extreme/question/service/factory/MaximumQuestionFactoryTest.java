package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.assertj.Assertions.assertThat;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MAXIMUM;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import no.lundesgaard.startup.extreme.question.model.Question;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MaximumQuestionFactory.class)
@DisplayName("maximum question factory tests")
public class MaximumQuestionFactoryTest extends QuestionFactoryTest {
    @Autowired
    private MaximumQuestionFactory factory;

    public MaximumQuestionFactoryTest() {
        super(MAXIMUM, 40);
    }

    @ParameterizedTest
    @MethodSource("questions")
    @DisplayName("return correct random question")
    void generateRandomQuestion(int[] numbers, String expectedText, String expectedAnswer) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        int[] candidateNumbers = IntStream.iterate(0, n -> n++).limit(100).toArray();
        when(randomizer.nextQuestionId()).thenReturn(id);
        when(randomizer.initNumbers(candidateNumbers)).thenReturn(numbers);
        Question result = factory().generateRandomQuestion();
        assertAll("question",
                () -> assertThat(result).where().id().isEqualTo(id),
                () -> assertThat(result).where().type().isEqualTo(questionType),
                () -> assertThat(result).where().points().isEqualTo(points),
                () -> assertThat(result).where().text().isEqualTo(expectedText),
                () -> assertThat(result).where().answer().isEqualTo(expectedAnswer));
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments(new int[] { 4, 2, 10, 7 }, "which of the following numbers is the largest: 4, 2, 10, 7", "10"),
                arguments(new int[] { 14, 21, 1, 3 }, "which of the following numbers is the largest: 14, 21, 1, 3", "21")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
