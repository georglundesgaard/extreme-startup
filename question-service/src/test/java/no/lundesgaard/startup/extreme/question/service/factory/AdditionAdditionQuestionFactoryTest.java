package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.ADDITION_ADDITION;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AdditionAdditionQuestionFactory.class)
@DisplayName("addition addition question factory tests")
class AdditionAdditionQuestionFactoryTest extends MathsQuestionFactoryTest {
    @Autowired
    private AdditionAdditionQuestionFactory factory;

    public AdditionAdditionQuestionFactoryTest() {
        super(ADDITION_ADDITION, 30);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments(new int[] {   2, 5, 3 }, "what is 2 plus 5 plus 3", "10"),
                arguments(new int[] {   6, 4, 7 }, "what is 6 plus 4 plus 7", "17")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
