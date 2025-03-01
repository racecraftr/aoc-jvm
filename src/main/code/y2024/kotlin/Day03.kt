package y2024.kotlin

import day.Day

class Day3: Day(2024, 3){

    private fun calcMul(s: String): Int {
        return Regex("\\d+").findAll(s).toList().map { it.value.toInt() }.run { this[0] * this[1] }
    }

    override fun part1(): Any {
        return Regex("mul\\(\\d+,\\d+\\)").findAll(input).sumOf { calcMul(it.value) }
    }

    override fun part2(): Any {
        val re = Regex("(mul\\(\\d+,\\d+\\))|(do(n't)?\\(\\))")
        var sum = 0; var enabled = true
        for (statement in re.findAll(input).map { it.value }) {
            if (statement.startsWith("mul") && enabled) {
                sum += calcMul(statement)
            } else enabled = statement == "do()"
        }
        return sum
    }
}

fun main() {
    Day3().run()
}