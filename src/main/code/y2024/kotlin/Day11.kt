package y2024.kotlin

import day.Day
import kotlin.math.log10
import kotlin.math.pow

class Day11 : Day(2024, 11) {

    private fun blink(stones: Map<Long, Long>): Map<Long, Long> {
        val newStones = mutableMapOf<Long, Long>()
        for ((stone, count) in stones.entries) {
            if (stone == 0L) {
                newStones[1] = newStones.getOrDefault(1, 0L) + count
                continue
            }
            val digits = log10(stone.toDouble()).toInt() + 1
            if (digits % 2 == 0) {
                val filter = (10.0).pow(digits / 2).toLong()
                val (left, right) = (stone/filter to stone%filter)
                newStones[left] = newStones.getOrDefault(left, 0L) + count
                newStones[right] = newStones.getOrDefault(right, 0L) + count
            } else {
                newStones[stone * 2024] = newStones.getOrDefault(stone * 2024, 0L) + count
            }
        }
        return newStones
    }

    override fun part1(): Any {
        var mp = input.split(Regex("\\s+")).map { it.toLong() to 1L }.toMap()
        (0 until 25).forEach { mp = blink(mp) }
        return mp.values.sum()
    }

    override fun part2(): Any {
        var mp = input.split(Regex("\\s+")).map { it.toLong() to 1L }.toMap()
        (0 until 75).forEach { mp = blink(mp) }
        return mp.values.sum()
    }
}

fun main() {
    Day11().run()
}