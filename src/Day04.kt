import java.lang.IllegalArgumentException

fun main() {
    fun getSections(line: String): Pair<IntRange, IntRange> {
        val elves = line.split(',')
        val (first, second) = elves.map { elf ->
            val (start, end) = elf.split('-').map { it.toInt() }
            start .. end
        }
        return Pair(first, second)
    }

    fun IntRange.containsAll(other: IntRange): Boolean {
        return start <= other.first && endInclusive >= other.last
    }

    fun Pair<IntRange, IntRange>.oneIsFullyContained(): Boolean {
        return first.containsAll(second) || second.containsAll(first)
    }

    fun IntRange.overlaps(other: IntRange): Boolean {
        return this.any { other.contains(it) }
    }

    fun part1(input: List<String>): Int {
        return input.count { getSections(it).oneIsFullyContained() }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val sections = getSections(it)
            sections.first.overlaps(sections.second)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
