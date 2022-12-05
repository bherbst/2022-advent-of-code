import java.lang.IllegalArgumentException

fun main() {
    fun findCommonChar(line: String): Char {
        val halfLength = line.length / 2
        val firstHalf = line.take(halfLength).toSet()
        val secondHalf = line.takeLast(halfLength).toSet()

        return firstHalf.first { secondHalf.contains(it) }
    }

    fun Char.toPriority(): Int {
        return if (isUpperCase()) {
            code - 'A'.code + 27
        } else {
            code - 'a'.code + 1
        }
    }

    fun findBadge(lines: List<String>): Char {
        return lines.map { it.toSet() }
            .reduce { common, other ->
                common intersect other
            }
            .first()
    }

    fun part1(input: List<String>): Int {
        return input.map { findCommonChar(it) }
            .sumOf { it.toPriority() }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(size = 3, step = 3)
            .map { findBadge(it) }
            .sumOf { it.toPriority() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
