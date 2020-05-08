package no.lundesgaard.startup.extreme.question.service;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.when;

import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("randomizer tests")
class RandomizerTest {
    private static final QuestionType[] QUESTION_TYPES = { ADDITION, SUBTRACTION, MULTIPLICATION, POWER };
    
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
    @ValueSource(strings = {"0", "1", "2", "3"})
    @DisplayName("next question type can return all question types")
    void nextQuestionType(int index) {
        when(randomizer.nextInt(QUESTION_TYPES.length)).thenReturn(index);
        QuestionType result = randomizer.nextQuestionType(1);
        assertThat(result).isEqualTo(QUESTION_TYPES[index]);
    }
}
