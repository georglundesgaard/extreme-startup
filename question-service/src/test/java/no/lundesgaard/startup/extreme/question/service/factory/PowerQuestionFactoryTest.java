package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.POWER;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PowerQuestionFactory.class)
@DisplayName("power question factory tests")
class PowerQuestionFactoryTest extends MathsQuestionFactoryTest {
    @Autowired
    private PowerQuestionFactory factory;

    public PowerQuestionFactoryTest() {
        super(POWER, 20);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments(new int[] { 2, 5 }, "what is 2 to the power of 5", "32"),
                arguments(new int[] { 6, 4 }, "what is 6 to the power of 4", "1296")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
