package no.lundesgaard.extremestartup.question

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.String.format
import kotlin.random.Random.Default.nextInt

@RestController
class QuestionController {
    @GetMapping("/")
    fun getQuestionSummary(): String {
        val r = nextInt(100)
        val q = if (r < 33) AdditionQuestion() else if (r < 66) SubtractionQuestion() else MultiplicationQuestion()
        return format("%s: %s? %s\n", q.id, q, q.correctAnswer())
    }
} 
