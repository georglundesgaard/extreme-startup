package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_MULTIPLICATION;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.MULTIPLICATION_ADDITION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MultiplicationAdditionQuestionFactory.class)
@DisplayName("multiplication addition question factory tests")
class MultiplicationAdditionQuestionFactoryTest extends TernaryMathsQuestionFactoryTest {
    @Autowired
    private MultiplicationAdditionQuestionFactory factory;

    public MultiplicationAdditionQuestionFactoryTest() {
        super(MULTIPLICATION_ADDITION, 50);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 2, 5, 3, "what is 2 multiplied by 5 plus 3", "13"),
                arguments("babecafe", 6, 4, 7, "what is 6 multiplied by 4 plus 7", "31")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
