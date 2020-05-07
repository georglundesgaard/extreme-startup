package no.lundesgaard.startup.extreme.question.service;

import static no.lundesgaard.startup.extreme.question.assertj.Assertions.assertThat;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.WARMUP;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import no.lundesgaard.startup.extreme.question.service.factory.AdditionQuestionFactory;
import no.lundesgaard.startup.extreme.question.service.factory.SubtractionQuestionFactory;
import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = QuestionService.class)
@DisplayName("question service tests")
class QuestionServiceTest {
    @Autowired
    private QuestionService service;

    @MockBean
    private Randomizer randomizer;

    @MockBean
    private AdditionQuestionFactory additionQuestionFactory;

    @MockBean
    private SubtractionQuestionFactory subtractionQuestionFactory;
    
    private final Map<QuestionType, Question> questionMap = new HashMap<>();
    private final Question addQuestion = new Question("addQuestion", ADDITION, 10, "what is 2 plus 2", "4");
    private final Question subQuestion = new Question("subQuestion", SUBTRACTION, 10, "what is 2 minus 2", "0");
    
    @BeforeEach
    void setUp() {
        questionMap.put(ADDITION, addQuestion);
        questionMap.put(SUBTRACTION, subQuestion);
        when(additionQuestionFactory.getQuestionType()).thenReturn(ADDITION);
        when(additionQuestionFactory.generateRandomQuestion()).thenReturn(addQuestion);
        when(subtractionQuestionFactory.getQuestionType()).thenReturn(SUBTRACTION);
        when(subtractionQuestionFactory.generateRandomQuestion()).thenReturn(subQuestion);
    }

    @ParameterizedTest
    @ValueSource(strings = { "ADDITION", "SUBTRACTION" })
    @DisplayName("call service method to generate random question")
    void generateRandomQuestion(QuestionType type) {
        int round = 1;
        when(randomizer.nextQuestionType(round)).thenReturn(type);
        Question result = service.generateRandomQuestion(round);
        assertThat(result).as("question from expected factory").isSameAs(questionMap.get(type));
    }

    @Test
    @DisplayName("call service method to genereate warmup question")
    void createWarmupQuestion() {
        when(randomizer.nextQuestionId()).thenReturn("cafebabe", "babecafe");
        Question result = service.createWarmupQuestion("player");
        assertThat(result).where().id().isEqualTo("cafebabe");
        assertThat(result).where().type().isEqualTo(WARMUP);
        assertThat(result).where().points().isEqualTo(10);
        assertThat(result).where().text().isEqualTo("what is your name");
        assertThat(result).where().answer().isEqualTo("player");
        assertThat(result).isNotEqualTo(service.createWarmupQuestion("player"));
    }
}
