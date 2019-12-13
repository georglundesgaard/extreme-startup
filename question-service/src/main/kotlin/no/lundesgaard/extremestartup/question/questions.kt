package no.lundesgaard.extremestartup.question

import java.util.*
import kotlin.random.Random.Default.nextInt

fun questionId() = UUID.randomUUID().toString().substring(0, 8)

abstract class Question<A>(val id: String = questionId()) {
    abstract fun correctAnswer(): A
}

abstract class BinaryMathsQuestion(val n1: Int = nextInt(20), val n2: Int = nextInt(20)): Question<Int>()

class AdditionQuestion: BinaryMathsQuestion() {
    override fun toString() = "what is %d plus %d".format(n1, n2)
    override fun correctAnswer() = n1 + n2
}

class SubtractionQuestion: BinaryMathsQuestion() {
    override fun toString() = "what is %d minus %d".format(n1, n2)
    override fun correctAnswer() = n1 - n2
}

class MultiplicationQuestion: BinaryMathsQuestion() {
    override fun toString() = "what is %d multiplied by %d".format(n1, n2)
    override fun correctAnswer() = n1 * n2 
}
