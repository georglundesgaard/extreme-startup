package no.lundesgaard.startup.extreme.question.model;

import lombok.Value;

@Value
public class Question {
    String id;
    QuestionType type;
    int points;
    String text;
    String answer;
}
