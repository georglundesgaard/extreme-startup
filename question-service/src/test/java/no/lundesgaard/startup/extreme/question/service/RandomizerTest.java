package no.lundesgaard.startup.extreme.question.service;

import static java.util.Arrays.stream;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MAXIMUM;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION_ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("randomizer tests")
class RandomizerTest {
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
    
    @MockBean(answer = CALLS_REAL_METHODS)
    private Randomizer randomizer;

    @Test
    @DisplayName("next int returns a valid integer")
    void nextInt() {
        assertThat(randomizer.nextInt(20)).isBetween(0, 19);
    }

    @Test
    @DisplayName("next question id has valid format and is unique")
    void nextQuestionId() {
        String result = randomizer.nextQuestionId();
        assertAll("nextQuestionId",
                () -> assertThat(result)
                        .isNotEmpty()
                        .matches("[0123456789abcdef]{8}$"),
                () -> assertThat(result).isNotEqualTo(randomizer.nextQuestionId()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2", "3", "4", "5", "6", "7", "8"})
    @DisplayName("next question type can return all question types")
    void nextQuestionType(int index) {
        when(randomizer.nextInt(QUESTION_TYPES.length)).thenReturn(index);
        QuestionType result = randomizer.nextQuestionType(1);
        assertThat(result).isEqualTo(QUESTION_TYPES[index]);
    }

    @ParameterizedTest
    @MethodSource("numbers")
    @DisplayName("init numbers returns a correct integer array")
    void initNumbers(int[] candidateNumbers, int[] shuffledCandidateNumbers, int[] numbers, int[] shuffledNumbers, int nextInt) {
        when(randomizer.nextInt(2)).thenReturn(nextInt);
        when(randomizer.nextInt(1000)).thenReturn(numbers[0], stream(numbers).limit(nextInt + 1).skip(1).boxed().toArray(Integer[]::new));
        when(randomizer.shuffle(candidateNumbers)).thenReturn(shuffledCandidateNumbers);
        when(randomizer.shuffle(numbers)).thenReturn(shuffledNumbers);
        int[] result = randomizer.initNumbers(candidateNumbers);
        assertThat(result).isEqualTo(shuffledNumbers);
    }

    private static Stream<Arguments> numbers() {
        return Stream.of(
                arguments(new int[] { 1, 2, 3, 4, 5 }, new int[] { 5, 4, 3, 2, 1 }, new int[] { 123, 567, 5, 4 }, new int[] { 4, 5, 567, 123 }, 1),
                arguments(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 5, 2, 4, 1 }, new int[] { 666, 471, 121, 3, 5, 2 }, new int[] { 2, 5, 3, 121, 471, 666 }, 2)
        );
    }

    @Test
    @DisplayName("shuffle returns a valid shuffled integer array")
    void shuffle() {
        int[] numbers = { 1, 2, 3, 4, 5 };
        int[] result = randomizer.shuffle(numbers);
        assertThat(result)
                .hasSize(5)
                .isNotEqualTo(numbers)
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }
}
