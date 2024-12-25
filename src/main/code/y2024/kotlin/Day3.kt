package y2024.kotlin

import day.Day

class Day3: Day(2024, 3){


    override fun part1(): Int {
        val re = Regex("mul\\(\\d+,\\d+\\)")
        val digRe = Regex("\\d+")
        var sum = 0
        for (statement in re.findAll(input)) {
            val s = statement.value
            val nums = digRe.findAll(s).toList().map { it.value.toInt() }
            sum += nums[0] * nums[1]
        }
        return sum
    }

    override fun part2(): Int {
        val re = Regex("(mul\\(\\d+,\\d+\\))|(do(n't)*\\(\\))")
        val digRe = Regex("\\d+")
        var sum = 0; var enabled = true
        for (statement in re.findAll(input)) {
            val s = statement.value
            if (s.startsWith("mul") && enabled) {
                val nums = digRe.findAll(s).toList().map { it.value.toInt() }
                sum += nums[0] * nums[1]
            }
            if (s.startsWith("do")) {
                enabled = true
            }
            if (s.startsWith("don't")) {
                enabled = false
            }
        }
        return sum
    }
}

fun main() {
    Day3().run()
}