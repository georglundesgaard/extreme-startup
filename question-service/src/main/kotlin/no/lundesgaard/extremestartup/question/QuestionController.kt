package no.lundesgaard.extremestartup.question

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.String.format
import kotlin.random.Random.Default.nextInt

@RestController
class QuestionController {
    private val questionMap = questionMap()
    private val questions: HashMap<String, Question> = HashMap()
    
    @GetMapping("/types")
    fun types(): String = format("%s\n", questionMap.keys.joinToString())
    
    @GetMapping("/random")
    fun randomQuestion(): String {
        val i = nextInt(questionMap.size)
        val q = questionMap.values.toList()[i].call()
        return processQuestion(q)
    }

    @GetMapping("/warmup")
    fun warmupQuestion(@RequestParam(required = false, defaultValue = "unknown") p: String): String {
        val q = WarmupQuestion(p)
        return processQuestion(q)
    }
    
    @GetMapping("/{idOrType}")
    fun question(@PathVariable idOrType: String): String =
            processQuestion(questions[idOrType] ?: questionMap[idOrType]?.call() ?: throw MissingIdOrTypeException(idOrType))

    private fun processQuestion(q: Question): String {
        questions[q.id] = q
        return format("%s\n", q)
    }

    @PostMapping("/{id}")
    fun checkAnswer(@PathVariable id: String, @RequestParam(required = false, defaultValue = "") a: String): String =
            format("%s\n", questions[id]?.checkAnswer(a) ?: throw MissingIdException(id))
    
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): String = format("%s\n", questions.remove(id)?.id ?: "")
    
    @GetMapping("/")
    fun questionCount(): String = format("%d\n", questions.size)
}

@ResponseStatus(NOT_FOUND)
class MissingIdOrTypeException(idOrType: String): RuntimeException(format("Unknown question id or type: %s", idOrType))

@ResponseStatus(NOT_FOUND)
class MissingIdException(id: String): RuntimeException(format("Unknown question id: %s", id))
