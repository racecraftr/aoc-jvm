package y2024.kotlin

import day.Day

class Day22: Day(2024, 22) {

    private fun parseInput(): List<Long> {
        return input.lines().map { it.toLong() }
    }

    private val PRUNE = 16777216L

    private fun step(num: Long): Long {
        var res = ((num * 64) xor num) % PRUNE
        res = ((res / 32) xor res) % PRUNE
        res = ((res * 2048) xor res) % PRUNE
        return res
    }

    override fun part1(): Any {
        var secretNums = parseInput()
        for (i in (0 until 2000)) {
            secretNums = secretNums.map { step(it) }
        }
        return secretNums.sum()
    }

    override fun part2(): Any {
        val secretNums = parseInput()
        val changeMap = HashMap<List<Long>, Long>()
        for (n in secretNums) {
            var secretNum = n
            val changes = LongArray(2000)
            var currentPrice = secretNum % 10

            val currentChangeMap = HashMap<List<Long>, Long>()

            for (i in 0 until 2000) {
                secretNum = step(secretNum)
                val newPrice = secretNum % 10
                val diff = newPrice - currentPrice

                changes[i] = diff
                if (i >= 3) {
                    val ch = changes.slice(i-3.. i)
                    if (!currentChangeMap.containsKey(ch)) {
                        currentChangeMap[ch] = newPrice
                    }
                }

                currentPrice = newPrice
            }

            for (entry in currentChangeMap) {
                changeMap[entry.key] = changeMap.getOrDefault(entry.key, 0) + entry.value
            }
        }
        return changeMap.values.max()
    }

}

fun main() {
    Day22().run()
}