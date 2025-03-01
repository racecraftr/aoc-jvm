package y2024.kotlin

import day.Day

class Day25 : Day(2024, 25) {

    override fun part1(): Any {
        val strToBin = { s: String -> s.toCharArray()
            .withIndex().sumOf { (if (it.value == '#') 1L else 0L) shl it.index }}
        val items = input.split("\n\n")
        val locks = items.filter { it[0] == '#' }.map { strToBin(it) }
        val keys = items.filter { it[0] == '.' }.map { strToBin(it) }
        return keys.sumOf { key -> locks.filter { (it and key) == 0L }.size }
    }

    override fun part2(): Any {
        return "WE DID IT LET'S GO"
    }
}

fun main() {
    Day25().run()
}