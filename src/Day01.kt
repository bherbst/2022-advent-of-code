fun main() {

    fun getCalories(input: List<String>): List<Int> {
        var index = 0;
        return input.fold(mutableListOf(0)) { values, line ->
            if (line.isBlank()) {
                index++
                values.add(0)
            } else {
                values[index] += line.toInt()
            }
            values
        }
    }

    fun part1(input: List<String>): Int {
        return getCalories(input).max()
    }

    fun part2(input: List<String>): Int {
        return getCalories(input)
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
