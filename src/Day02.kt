import java.lang.IllegalArgumentException
import kotlin.math.round

fun main() {

    fun parseChoice(char: Char): Choice {
        return when (char) {
            'A', 'X' -> Choice.Rock
            'B', 'Y' -> Choice.Paper
            'C', 'Z' -> Choice.Scissors
            else -> throw IllegalArgumentException("Invalid char $char")
        }
    }

    fun parseResult(char: Char): Result  {
        return when (char) {
            'X' -> Result.Loss
            'Y' -> Result.Tie
            'Z' -> Result.Win
            else -> throw IllegalArgumentException("Invalid char $char")
        }
    }

    fun parseRound(line: String): Round {
        return Round(
            myChoice = parseChoice(line[2]),
            theirChoice = parseChoice(line[0])
        )
    }

    fun parseRound2(line: String): Round2 {
        return Round2(
            result = parseResult(line[2]),
            theirChoice = parseChoice(line[0])
        )
    }

    fun part1(input: List<String>): Int {
        return input.map { parseRound(it) }
            .sumOf { it.getScore() }
    }

    fun part2(input: List<String>): Int {
        return input.map { parseRound2(it) }
            .sumOf { it.getScore() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private class Round(
    private val theirChoice: Choice,
    private val myChoice: Choice
) {

    companion object {
        private const val WIN = 6;
        private const val LOSS = 0;
        private const val TIE = 3;
    }

    fun getScore(): Int {
        return getResultScore() + myChoice.value
    }

    private fun getResultScore(): Int {
        return when (theirChoice) {
            Choice.Rock -> {
                when (myChoice) {
                    Choice.Rock -> TIE
                    Choice.Paper -> WIN
                    Choice.Scissors -> LOSS
                }
            }

            Choice.Paper -> {
                when (myChoice) {
                    Choice.Rock -> LOSS
                    Choice.Paper -> TIE
                    Choice.Scissors -> WIN
                }
            }

            Choice.Scissors -> {
                when (myChoice) {
                    Choice.Rock -> WIN
                    Choice.Paper -> LOSS
                    Choice.Scissors -> TIE
                }
            }
        }
    }
}

private class Round2(
    private val theirChoice: Choice,
    private val result: Result
) {

    fun getScore(): Int {
        return getMyChoice().value + result.value
    }

    private fun getMyChoice(): Choice {
        return when (theirChoice) {
            Choice.Rock -> {
                when (result) {
                    Result.Win -> Choice.Paper
                    Result.Loss -> Choice.Scissors
                    Result.Tie -> Choice.Rock
                }
            }

            Choice.Paper -> {
                when (result) {
                    Result.Win -> Choice.Scissors
                    Result.Loss -> Choice.Rock
                    Result.Tie -> Choice.Paper
                }
            }

            Choice.Scissors -> {
                when (result) {
                    Result.Win -> Choice.Rock
                    Result.Loss -> Choice.Paper
                    Result.Tie -> Choice.Scissors
                }
            }
        }
    }
}

private enum class Choice(val value: Int) {
    Rock(1),
    Paper(2),
    Scissors(3)
}

private enum class Result(val value: Int) {
    Win(6),
    Loss(0),
    Tie(3)
}
