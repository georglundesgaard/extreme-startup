package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;
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
@ContextConfiguration(classes = FibonacciQuestionFactory.class)
@DisplayName("power question factory tests")
class FibonacciQuestionFactoryTest extends BinaryMathsQuestionFactoryTest {
    @Autowired
    private FibonacciQuestionFactory factory;

    public FibonacciQuestionFactoryTest() {
        super(FIBONACCI, 50);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments("cafebabe", 0, 0, "what is the 4th number in the Fibonacci sequence", "3"),
                arguments("cafebabe", 7, 0, "what is the 11th number in the Fibonacci sequence", "89"),
                arguments("cafebabe", 8, 0, "what is the 12th number in the Fibonacci sequence", "144"),
                arguments("cafebabe", 9, 0, "what is the 13th number in the Fibonacci sequence", "233"),
                arguments("cafebabe", 10, 0, "what is the 14th number in the Fibonacci sequence", "377"),
                arguments("cafebabe", 17, 0, "what is the 21st number in the Fibonacci sequence", "10946"),
                arguments("cafebabe", 18, 0, "what is the 22nd number in the Fibonacci sequence", "17711"),
                arguments("cafebabe", 19, 0, "what is the 23rd number in the Fibonacci sequence", "28657")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
