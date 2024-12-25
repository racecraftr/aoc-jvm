package y2024.kotlin

import day.Day

class Day25 : Day(2024, 25) {

    val maxPinHeight = 7
    val identifier = "#####"

    private open inner class PinObject {

        val pinHeights: List<Int>

        constructor(ls: List<String>) {
            val charMtx = ls.map { it.toCharArray().toList() }
            pinHeights = MutableList(charMtx[0].size) { 0 }
            for (col in 0 until charMtx[0].size) {
                for (row in charMtx.indices) {
                    pinHeights[col] += if (charMtx[row][col] == '#') 1 else 0
                }
            }
        }

        fun fitsWith(other: PinObject): Boolean {
            return pinHeights.zip(other.pinHeights).map { it.first + it.second }.all { it <= maxPinHeight }
        }
    }

    private inner class Lock(ls: List<String>) : PinObject(ls)

    private inner class Key(ls: List<String>) : PinObject(ls)

    private fun parseInput(): Pair<List<Key>, List<Lock>> {
        val items = input.split("\n\n").map { it.lines() }
        var keys = mutableListOf<Key>()
        var locks = mutableListOf<Lock>()
        for (item in items) {
            if (item.first() == identifier) {
                locks.add(Lock(item))
            } else if (item.last() == identifier) {
                keys.add(Key(item))
            }
        }
        return Pair(keys, locks)
    }

    override fun part1(): Any {
        val (keys, locks) = parseInput()
        return keys.sumOf { key -> locks.filter { it.fitsWith(key) }.size }
    }

    override fun part2(): Any {
        return "WE DID IT LET'S FUCKING GO"
    }
}

fun main() {
    Day25().run()
}