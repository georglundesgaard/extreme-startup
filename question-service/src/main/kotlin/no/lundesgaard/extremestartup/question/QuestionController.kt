package no.lundesgaard.extremestartup.question

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.String.format
import kotlin.random.Random.Default.nextInt

@RestController
class QuestionController {
    private val questions: HashMap<String, Question<Int>> = HashMap() 
    
    @GetMapping("/random")
    fun randomQuestion(): String {
        val r = nextInt(100)
        val q = if (r < 33) AdditionQuestion() else if (r < 66) SubtractionQuestion() else MultiplicationQuestion()
        questions[q.id] = q
        return format("%s\n", q)
    }
    
    @GetMapping("/{id}")
    fun question(@PathVariable id: String): String {
        val q = questions[id] ?: throw MissingQuestionException(id)
        return format("%s\n", q)
    }
    
    @PostMapping("/{id}")
    fun checkAnswer(@PathVariable id: String, @RequestBody answer: String): String {
        val q = questions[id] ?: throw MissingQuestionException(id)
        return format("%s\n", answer == q.correctAnswer().toString())
    }
    
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String):String {
        val q = questions.remove(id)
        return format("%s\n", q?.id ?: "")
    }
    
    @GetMapping("/")
    fun questions(): String = format("%d\n", questions.size)
}

@ResponseStatus(NOT_FOUND)
class MissingQuestionException(id: String): RuntimeException(format("Unknown question id: %s", id))
