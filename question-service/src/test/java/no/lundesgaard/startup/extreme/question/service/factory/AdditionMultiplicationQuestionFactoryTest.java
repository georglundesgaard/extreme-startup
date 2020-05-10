package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_MULTIPLICATION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AdditionMultiplicationQuestionFactory.class)
@DisplayName("addition multiplication question factory tests")
class AdditionMultiplicationQuestionFactoryTest extends TernaryMathsQuestionFactoryTest {
    @Autowired
    private AdditionMultiplicationQuestionFactory factory;

    public AdditionMultiplicationQuestionFactoryTest() {
        super(ADDITION_MULTIPLICATION, 60);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 2, 5, 3, "what is 2 plus 5 multiplied by 3", "17"),
                arguments("babecafe", 6, 4, 7, "what is 6 plus 4 multiplied by 7", "34")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
