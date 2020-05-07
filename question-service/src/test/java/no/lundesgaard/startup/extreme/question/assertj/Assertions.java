package no.lundesgaard.startup.extreme.question.assertj;

import no.lundesgaard.startup.extreme.question.model.Question;

public class Assertions {
    public static QuestionAssert assertThat(Question question) {
        return new QuestionAssert(question);
    }
}
