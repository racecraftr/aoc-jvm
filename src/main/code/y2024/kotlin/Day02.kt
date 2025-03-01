package y2024.kotlin

import day.Day
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day2 : Day(2024, 2) {

    private fun parseInput(): List<List<Int>> {
        return input.lines().map { ln -> ln.split(" ").map { it.toInt() } }
    }

    private fun isValid(levels: List<Int>): Boolean {
        val changeSign = (levels[1] - levels[0]).sign
        return (0 until (levels.size - 1)).map { levels[it + 1] - levels[it] }.all { change ->
            change.absoluteValue >= 1 && change.absoluteValue <= 3 && change.sign == changeSign
        }
    }

    override fun part1(): Any {
        return parseInput().filter { isValid(it) }.size
    }

    override fun part2(): Any {
        return parseInput().map { levels ->
            if (levels.indices.map { i -> levels.toMutableList().also { it.removeAt(i) } }.any { isValid(it) }) 1 else 0
        }.sum()
    }
}

fun main() {
    Day2().run()
}