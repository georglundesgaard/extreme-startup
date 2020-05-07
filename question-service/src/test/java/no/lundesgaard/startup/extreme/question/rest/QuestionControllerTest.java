package no.lundesgaard.startup.extreme.question.rest;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.WARMUP;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.service.QuestionService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(QuestionController.class)
@DisplayName("question controller tests")
class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService service;
    
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    @DisplayName("send GET-request to /random to generate random question")
    void generateRandomQuestion(int round) throws Exception {
        when(service.generateRandomQuestion(round)).thenReturn(new Question("cafebabe", ADDITION, 10, "what is 2 plus 2", "4"));
        String expectedJson = """
                {
                    "id" : "cafebabe",
                    "type" : "ADDITION",
                    "points" : 10,
                    "text" : "what is 2 plus 2",
                    "answer" : "4"
                }
                """;
        mockMvc.perform(get("/random").param("round", String.valueOf(round)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"player1", "player2"})
    @DisplayName("send GET-request to /warmp to create warmup question")
    void createWarmupQuestion(String player) throws Exception {
        when(service.createWarmupQuestion(player)).thenReturn(new Question("cafebabe", WARMUP, 10, "what is your name", player));
        String expectedJson = format("""
                {
                    "id" : "cafebabe",
                    "type" : "WARMUP",
                    "points" : 10,
                    "text" : "what is your name",
                    "answer" : "%s"
                }
                """, player);
        mockMvc.perform(get("/warmup").param("player", player))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
    }
}
