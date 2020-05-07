package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MultiplicationQuestionFactory.class)
@DisplayName("multiplication question factory tests")
class MultiplicationQuestionFactoryTest extends BinaryMathsQuestionFactoryTest {
    @Autowired
    private MultiplicationQuestionFactory factory;

    public MultiplicationQuestionFactoryTest() {
        super(MULTIPLICATION);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 2, 5, "what is 2 multiplied by 5", "10"),
                arguments("babecafe", 6, 4, "what is 6 multiplied by 4", "24")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
