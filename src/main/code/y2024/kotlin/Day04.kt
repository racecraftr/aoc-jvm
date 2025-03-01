package y2024.kotlin

import day.Day
import util.Point

class Day04: Day(2024, 4) {

    val dirs = listOf(
        Point(0, 1),
        Point(0, -1),

        Point(1, 0),
        Point(-1, 0),

        Point(1, 1),
        Point(1, -1),

        Point(-1, 1),
        Point(-1, -1)
    )

    fun parseInput(): List<List<Char>> {
        return input.lines().map { it.toCharArray().toList() }
    }

    override fun part1(): Any {
        var total = 0
        val grid = parseInput()
        val strCheck = "XMAS"
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                total += dirs.map { dir -> (0..<strCheck.length).map { Point(x, y) + dir * it} }
                    .map { points -> points.map { it.atOrDefault(grid, ' ') }.joinToString("") }
                    .filter { it == strCheck }.size
            }
        }
        return total
    }

    override fun part2(): Any {
        var total = 0
        val grid = parseInput()
        for ((y, row) in grid.withIndex()) {
            for ((x, c) in row.withIndex()) {
                if (c != 'A') {
                    continue
                }
                val (diag1, diag2) = listOf(Point(-1, -1), Point(1, 1), Point(-1, 1), Point(1, -1))
                    .map { (Point(x, y) + it).atOrDefault(grid, ' ') }
                    .chunked(2)
                    .map { it.joinToString("") }
                if ((diag1 == "MS" || diag1 == "SM") && (diag2 == "MS" || diag2 == "SM")) {
                    total++
                }
            }
        }
        return total
    }

}

fun main() {
    Day04().run()
}