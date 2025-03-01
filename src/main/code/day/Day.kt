package day

import util.readFile
import java.util.Calendar

abstract class Day(private val year: Int, private val day: Int) {

    init {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        if (year < 2015 || year > currentYear) {
            throw IllegalArgumentException("Invalid input")
        }
        if (day > 25 || day < 1) {
            throw IllegalArgumentException("Invalid input")
        }
    }
    protected var input = readFile(year, day)
    protected abstract fun part1(): Any
    protected abstract fun part2(): Any

    fun run() {
        println("Running Year $year day $day")
        val start = System.currentTimeMillis()
        val p1 = part1()
        println("Part 1: $p1")
        val p2 = part2()
        val end = System.currentTimeMillis()
        println("Part 2: $p2")
        println("Execution Time: ${(end - start).toFloat() / 1000} seconds")
    }
}
