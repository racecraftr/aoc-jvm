package y2024.kotlin

import day.Day

class Day13 : Day(2024, 13) {

    private operator fun <T> List<T>.component6(): T = get(5)

    private inner class Machine(val ls: List<Long>) {

        constructor(s: String) : this(Regex("\\d+").findAll(s).map { it.value.toLong() }.toList())

        fun cost(offset: Long): Long {
            var (ax, ay, bx, by, px, py) = ls
            px += offset
            py += offset
            val maNum = px * by - py * bx
            val maDenom = ax * by - ay * bx
            val ma = maNum / maDenom
            if (maNum % maDenom != 0L) return 0

            val mbNum = py - ay * ma
            val mbDenom = by
            val mb = mbNum / mbDenom
            if (mbNum % mbDenom != 0L) return 0

            return 3 * ma + mb
        }
    }

    private fun parseInput(): List<Machine> {
        return input.split("\n\n").map { Machine(it) }
    }

    override fun part1(): Any {
        return parseInput().sumOf { it.cost(0)}
    }

    override fun part2(): Any {
        return parseInput().sumOf { it.cost(10000000000000)}
    }
}

fun main() {
    Day13().run()
}