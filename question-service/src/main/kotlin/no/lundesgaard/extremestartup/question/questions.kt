package no.lundesgaard.extremestartup.question

import no.lundesgaard.extremestartup.question.QuestionType.ADDITION
import no.lundesgaard.extremestartup.question.QuestionType.ADDITION_ADDITION
import no.lundesgaard.extremestartup.question.QuestionType.ADDITION_MULTIPLICATION
import no.lundesgaard.extremestartup.question.QuestionType.FIBONACCI
import no.lundesgaard.extremestartup.question.QuestionType.MAXIMUM
import no.lundesgaard.extremestartup.question.QuestionType.MULTIPLICATION
import no.lundesgaard.extremestartup.question.QuestionType.MULTIPLICATION_ADDITION
import no.lundesgaard.extremestartup.question.QuestionType.POWER
import no.lundesgaard.extremestartup.question.QuestionType.PRIMES
import no.lundesgaard.extremestartup.question.QuestionType.SQUARE_CUBE
import no.lundesgaard.extremestartup.question.QuestionType.SUBTRACTION
import no.lundesgaard.extremestartup.question.QuestionType.WARMUP
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random.Default.nextInt

fun questionMap() = linkedMapOf(
        ADDITION.typeName to ::AdditionQuestion,
        MAXIMUM.typeName to ::MaximumQuestion,
        MULTIPLICATION.typeName to ::MultiplicationQuestion,
        SQUARE_CUBE.typeName to ::SquareCubeQuestion,
//        GENERAL_KNOWLEDGE.typeName to GeneralKnowledgeQuestion(),
        PRIMES.typeName to ::PrimesQuestion,
        SUBTRACTION.typeName to ::SubtractionQuestion,
        FIBONACCI.typeName to ::FibonacciQuestion,
        POWER.typeName to ::PowerQuestion,
        ADDITION_ADDITION.typeName to ::AdditionAdditionQuestion,
        ADDITION_MULTIPLICATION.typeName to ::AdditionMultiplicationQuestion,
        MULTIPLICATION_ADDITION.typeName to ::MultiplicationAdditionQuestion
//        ANAGRAM to AnagramQuestion,
//        SCRABBLE to ScrabbleQuestion
)

private fun questionId() = UUID.randomUUID().toString().substring(0, 8)

private fun Int.pow(n: Int) = this.toDouble().pow(n).toInt()

private fun initNumbers(candidateNumbers: IntArray): IntArray {
    val size = nextInt(2) + 1
    return randomNumbers()
            .take(size)
            .union(candidateNumbers.toList()
                    .shuffled()
                    .subList(0, size))
            .shuffled()
            .toIntArray()
}

private fun randomNumbers() = IntArray(5) { nextInt(1000) }

private fun squareCubeCandidateNumbers(): IntArray {
    val squareCubes = IntArray(100) { (it + 1).pow(3) }.filter(::isSquare)
    val squares = IntArray(50).map { (it + 1).pow(2) }
    return squareCubes.union(squares).toIntArray()
}

private fun isSquare(number: Int) = number == 0 || number % sqrt(number.toDouble()).roundToInt() == 0

private fun isCube(number: Int) = number == 0 || number % number.toDouble().pow(1/3.toDouble()).roundToInt() == 0

private fun isPrime(number: Int) = (2..number/2).none{ number % it == 0 }

private fun primes(count: Int) = generateSequence(1){ it + 1 }.filter(::isPrime).take(count).toList().toIntArray()

enum class QuestionType(val typeName: String) {
    ADDITION("add"),
    MAXIMUM("max"),
    MULTIPLICATION("mult"),
    SQUARE_CUBE("sq_cb"),
    PRIMES("primes"),
    SUBTRACTION("sub"),
    FIBONACCI("fib"),
    POWER("pow"),
    ADDITION_ADDITION("add_add"),
    ADDITION_MULTIPLICATION("add_mult"),
    MULTIPLICATION_ADDITION("mult_add"),
    WARMUP("warmup");

    override fun toString() = typeName
}

abstract class Question(val id: String = questionId()) {
    abstract val type: QuestionType
    open val points = 10
    fun checkAnswer(answer: String) = (if (correctAnswer() == answer) points else -points).toString()
    abstract fun asText(): String
    abstract fun correctAnswer(): String
    override fun toString() = "%s: %s".format(id, asText())
}

abstract class BinaryMathsQuestion(val n1: Int = nextInt(20), val n2: Int = nextInt(20)) : Question()

abstract class TernaryMathsQuestion(val n1: Int = nextInt(20), val n2: Int = nextInt(20), val n3: Int = nextInt(20)) : Question()

abstract class SelectFromListOfNumbersQuestion(val candidateNumbers: IntArray, val numbers: IntArray = initNumbers(candidateNumbers)) : Question() {
    override fun correctAnswer(): String = numbers.filter { shouldBeSelected(it) }.joinToString()
    abstract fun shouldBeSelected(number: Int): Boolean
}

class MaximumQuestion : SelectFromListOfNumbersQuestion(IntArray(100) { it + 1 }) {
    override val type = MAXIMUM
    override val points = 40
    override fun asText() = "which of the following numbers is the largest: %s".format(numbers.joinToString())
    override fun shouldBeSelected(number: Int) = number == numbers.max()
}

class AdditionQuestion : BinaryMathsQuestion() {
    override val type = ADDITION
    override fun asText() = "what is %d plus %d".format(n1, n2)
    override fun correctAnswer() = (n1 + n2).toString()
}

class SubtractionQuestion : BinaryMathsQuestion() {
    override val type = SUBTRACTION
    override fun asText() = "what is %d minus %d".format(n1, n2)
    override fun correctAnswer() = (n1 - n2).toString()
}

class MultiplicationQuestion : BinaryMathsQuestion() {
    override val type = MULTIPLICATION
    override fun asText() = "what is %d multiplied by %d".format(n1, n2)
    override fun correctAnswer() = (n1 * n2).toString()
}

class AdditionAdditionQuestion : TernaryMathsQuestion() {
    override val type = ADDITION_ADDITION
    override val points = 30
    override fun asText() = "what is %d plus %d plus %d".format(n1, n2, n3)
    override fun correctAnswer() = (n1 + n2 + n3).toString()
}

class AdditionMultiplicationQuestion : TernaryMathsQuestion() {
    override val type = ADDITION_MULTIPLICATION
    override val points = 60
    override fun asText() = "what is %d plus %d multiplied by %d".format(n1, n2, n3)
    override fun correctAnswer() = (n1 + n2 * n3).toString()
}

class MultiplicationAdditionQuestion : TernaryMathsQuestion() {
    override val type = MULTIPLICATION_ADDITION
    override val points = 50
    override fun asText() = "what is %d multiplied by %d plus %d".format(n1, n2, n3)
    override fun correctAnswer() = (n1 * n2 + n3).toString()
}

class PowerQuestion : BinaryMathsQuestion() {
    override val type = POWER
    override val points = 20
    override fun asText() = "what is %d to the power of %d".format(n1, n2)
    override fun correctAnswer() = n1.pow(n2).toString()
}

class SquareCubeQuestion : SelectFromListOfNumbersQuestion(squareCubeCandidateNumbers()) {
    override val type = SQUARE_CUBE
    override val points = 60
    override fun asText() = "which of the following numbers is both a square and a cube: %s".format(numbers.joinToString())
    override fun shouldBeSelected(number: Int) = isSquare(number) && isCube(number)
}

class PrimesQuestion : SelectFromListOfNumbersQuestion(primes(100)) {
    override val type = PRIMES
    override val points = 60
    override fun asText() = "which of the following numbers are primes: %s".format(numbers.joinToString())
    override fun shouldBeSelected(number: Int) = isPrime(number)
}

class FibonacciQuestion : BinaryMathsQuestion() {
    override val type = FIBONACCI
    override val points = 50
    override fun asText() = "what is the %s number in the Fibonacci sequence".format(ordinalize(n1 + 4))
    override fun correctAnswer() = fibonacci(n1 + 4).toString()
    private fun ordinalize(number: Int) = "%d%s".format(number, ordinal(number))
    private fun ordinal(number: Int) =
            if ((11..13).contains(number.absoluteValue % 100))
                "th"
            else when (number.absoluteValue % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }

    private fun fibonacci(number: Int, a: Int = 0, b: Int = 1): Int = if (number > 0) fibonacci(number - 1, b, a + b) else a
}

class WarmupQuestion(private val playerName: String = "unknown") : Question() {
    override val type = WARMUP
    override fun asText() = "what is your name"
    override fun correctAnswer() = playerName
}
