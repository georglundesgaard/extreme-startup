package no.lundesgaard.startup.extreme.question;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

import no.lundesgaard.startup.extreme.question.model.QuestionType;
import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName("question application tests")
class QuestionApplicationTest {
    @MockBean
    private Randomizer randomizer;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @LocalServerPort
    private int serverPort;

    @ParameterizedTest
    @ValueSource(strings = {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "POWER", "FIBONACCI"})
    @DisplayName("generate random question for each question type")
    void generateRandomQuestion(QuestionType questionType) {
        when(randomizer.nextQuestionType(1)).thenReturn(questionType);
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(format("http://localhost:%d/random?round=1", serverPort), String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }
}
