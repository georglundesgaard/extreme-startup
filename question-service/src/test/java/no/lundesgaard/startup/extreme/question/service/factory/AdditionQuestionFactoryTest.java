package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AdditionQuestionFactory.class)
@DisplayName("addition question factory tests")
class AdditionQuestionFactoryTest extends BinaryMathsQuestionFactoryTest {
    @Autowired
    private AdditionQuestionFactory factory;

    public AdditionQuestionFactoryTest() {
        super(ADDITION);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 2, 5, "what is 2 plus 5", "7"),
                arguments("babecafe", 6, 4, "what is 6 plus 4", "10")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
