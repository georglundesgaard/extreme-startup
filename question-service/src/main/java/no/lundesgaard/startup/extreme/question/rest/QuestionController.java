package no.lundesgaard.startup.extreme.question.rest;

import no.lundesgaard.startup.extreme.question.model.Question;
import no.lundesgaard.startup.extreme.question.service.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/random")
    public Question generateRandomQuestion(@RequestParam int round) {
        return questionService.generateRandomQuestion(round);
    }
    
    @GetMapping("/warmup")
    public Question createWarmupQuestion(@RequestParam String player) {
        return questionService.createWarmupQuestion(player);
    }
}
