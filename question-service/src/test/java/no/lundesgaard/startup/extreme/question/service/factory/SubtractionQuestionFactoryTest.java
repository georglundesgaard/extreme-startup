package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.SUBTRACTION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SubtractionQuestionFactory.class)
@DisplayName("subtraction question factory tests")
class SubtractionQuestionFactoryTest extends BinaryMathsQuestionFactoryTest {
    @Autowired
    private SubtractionQuestionFactory factory;

    public SubtractionQuestionFactoryTest() {
        super(SUBTRACTION);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 2, 5, "what is 2 minus 5", "-3"),
                arguments("babecafe", 6, 4, "what is 6 minus 4", "2")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
