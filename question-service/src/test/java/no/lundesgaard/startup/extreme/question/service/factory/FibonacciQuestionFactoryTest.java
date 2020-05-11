package no.lundesgaard.startup.extreme.question.service.factory;

import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;
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
@DisplayName("fibonacci question factory tests")
class FibonacciQuestionFactoryTest extends MathsQuestionFactoryTest {
    @Autowired
    private FibonacciQuestionFactory factory;

    public FibonacciQuestionFactoryTest() {
        super(FIBONACCI, 50);
    }

    private static Stream<Arguments> questions() {
        return Stream.of(
                arguments(new int[] { 0 }, "what is the 4th number in the Fibonacci sequence", "3"),
                arguments(new int[] { 7 }, "what is the 11th number in the Fibonacci sequence", "89"),
                arguments(new int[] { 8 }, "what is the 12th number in the Fibonacci sequence", "144"),
                arguments(new int[] { 9 }, "what is the 13th number in the Fibonacci sequence", "233"),
                arguments(new int[] { 10 }, "what is the 14th number in the Fibonacci sequence", "377"),
                arguments(new int[] { 17 }, "what is the 21st number in the Fibonacci sequence", "10946"),
                arguments(new int[] { 18 }, "what is the 22nd number in the Fibonacci sequence", "17711"),
                arguments(new int[] { 19 }, "what is the 23rd number in the Fibonacci sequence", "28657")
        );
    }

    @Override
    protected QuestionFactory factory() {
        return factory;
    }
}
