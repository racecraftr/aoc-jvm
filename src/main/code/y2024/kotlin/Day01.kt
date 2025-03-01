package y2024.kotlin

import day.Day
import kotlin.math.absoluteValue

class Day1: Day(2024, 1) {

    private fun parseInput(): Pair<List<Long>, List<Long>> {
        return input.split("\n").map { it.split(Regex("\\s+")) }.map { it[0].toLong() to it[1].toLong() }.unzip()
    }

    override fun part1(): Any {
        return parseInput().run { first.sorted().zip(second.sorted()).sumOf{(it.first - it.second).absoluteValue} }
    }

    override fun part2(): Any {
        return parseInput().run { second.groupingBy { it }.eachCount().run { first.sumOf { it * (this[it]?:0) } } }
    }
}

fun main() {
    Day1().run()
}