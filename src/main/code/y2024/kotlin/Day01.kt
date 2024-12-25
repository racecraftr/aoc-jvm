package y2024.kotlin

import day.Day
import kotlin.math.absoluteValue

class Day1: Day(2024, 1) {

    private fun parseInput(): Pair<List<Int>, List<Int>> {
        val ws = Regex("\\s+")
        return input.split("\n").map { it.split(ws) }.map { it[0].toInt() to it[1].toInt() }.unzip()
    }

    override fun part1(): Int {
        val (l, r) = parseInput()
        return l.sorted().zip(r.sorted()).sumOf { (it.first - it.second).absoluteValue }
    }

    override fun part2(): Int {
        val (l, r) = parseInput()
        val freqs = HashMap<Int, Int>()
        r.forEach { freqs[it] = freqs.getOrDefault(it, 0) + 1 }
        return l.sumOf { it * freqs.getOrDefault(it, 0) }
    }
}

fun main() {
    Day1().run()
}