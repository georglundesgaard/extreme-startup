package no.lundesgaard.startup.extreme.question.service.factory;

import static java.lang.String.format;
import static no.lundesgaard.startup.extreme.question.model.QuestionType.FIBONACCI;

import no.lundesgaard.startup.extreme.question.service.Randomizer;

import org.springframework.stereotype.Component;

@Component
public class FibonacciQuestionFactory extends BinaryMathsQuestionFactory {
    public FibonacciQuestionFactory(Randomizer randomizer) {
        super(randomizer, FIBONACCI, 50, FibonacciQuestionFactory::text, FibonacciQuestionFactory::answer);
    }

    private static String text(int n1, int n2) {
        return format("what is the %s number in the Fibonacci sequence", ordinalize(n1 + 4));
    }

    private static String ordinalize(int number) {
        return format("%d%s", number, ordinal(number));
    }

    private static String ordinal(int n) {
        return switch (n) {
            case 11, 12, 13 -> "th";
            default -> switch (n % 10) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        };
    }

    private static String answer(int n1, int n2) {
        return String.valueOf(fibonacci(n1 + 4, 0, 1));
    }

    private static int fibonacci(int number, int a, int b) {
        if (number > 0) 
            return fibonacci(number - 1, b, a + b);
        return a;
    }
}
