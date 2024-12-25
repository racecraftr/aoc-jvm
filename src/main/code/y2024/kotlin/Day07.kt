package y2024.kotlin

import day.Day
import kotlin.math.pow

class Day7 : Day(2024, 7) {

    private val operators = listOf("+", "*", "||")

    private fun parseInput(): List<Pair<Long, List<Long>>> {
        val res = emptyList<Pair<Long, List<Long>>>().toMutableList()
        for (ln in input.lines()) {
            val parts = ln.split(": ")
            res.add(parts[0].toLong() to parts[1]
                .split(" ")
                .map { it.toLong() })
        }
        return res
    }

    private fun concat(i1: Long, i2: Long): Long {
        var n = 1
        while (i2 % n != i2) {
            n *= 10
        }
        return i1 * n + i2
    }

    private fun canMake(num: Long, operands: List<Long>, base: Long): Boolean {
        val perms = (base.toFloat()).pow(operands.size - 1).toLong()

        for (p in 0..<perms) {
            var perm = p
            var n = 0L
            for ((i, v) in operands.withIndex()) {
                if (i == 0) {
                    n = v
                    continue
                }
                when (operators[(perm % base).toInt()]) {
                    "+" -> n += v
                    "*" -> n *= v
                    "||" -> n = concat(n, v)
                }
                if (n == num && i == operands.size - 1) {
                    return true
                }
                if (n > num) {
                    break
                }
                perm /= base
            }
        }
        return false
    }

    override fun part1(): Any {
        return parseInput().filter { canMake(it.first, it.second, 2) }.sumOf { it.first }
    }

    override fun part2(): Any {
        return parseInput().filter { canMake(it.first, it.second, 3) }.sumOf { it.first }
    }
}

fun main() {
    Day7().run()
}