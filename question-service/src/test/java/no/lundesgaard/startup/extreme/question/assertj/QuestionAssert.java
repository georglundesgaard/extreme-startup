package no.lundesgaard.startup.extreme.question.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.model.QuestionType;

import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractStringAssert;

public class QuestionAssert extends AbstractObjectAssert<QuestionAssert, Question> {
    private static final Pattern QUESTION_ID_PATTERN = Pattern.compile("[0123456789abcdef]{8}$");

    public QuestionAssert(Question question) {
        super(question, QuestionAssert.class);
    }

    public QuestionAssert where() {
        return this;
    }

    public AbstractStringAssert<?> id() {
        return assertThat(actual.getId()).as("question id");
    }

    public AbstractComparableAssert<?, QuestionType> type() {
        return assertThat(actual.getType()).as("question type");
    }

    public AbstractIntegerAssert<?> points() {
        return assertThat(actual.getPoints()).as("question points");
    }

    public AbstractStringAssert<?> text() {
        return assertThat(actual.getText()).as("question text");
    }

    public AbstractStringAssert<?> answer() {
        return assertThat(actual.getAnswer()).as("question answer");
    }
}
