package y2024.kotlin

import day.Day
import util.Point
import java.util.LinkedList
import java.util.Queue

class Day10: Day(2024, 10) {

    val directions = listOf<Point>(
        Point(1, 0),
        Point(-1, 0),
        Point(0, 1),
        Point(0, -1)
    )

    private fun parseInput(): Pair<List<List<Char>>, List<Point>> {
        val mtx = input.lines().map { it.toList() }
        val trailHeads = mutableListOf<Point>()
        for ((y, line) in mtx.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char == '0') {
                    trailHeads.add(Point(x, y))
                }
            }
        }
        return (mtx to trailHeads)
    }

    private fun bfs(mtx: List<List<Char>>, p: Point, allowDupes: Boolean): Long {
        val visited = mutableSetOf<Point>()
        var count = 0L
        val queue: Queue<Point> = LinkedList()
        queue.add(p)
        while (queue.isNotEmpty()) {
            val deQ = queue.poll()
            if (visited.contains(deQ) && !allowDupes) {
                continue
            }

            val c = deQ.at(mtx)!!
            if (c == '9') {
                count ++
                visited.add(deQ)
            } else queue.addAll(directions.map { it+deQ }.filter { (it.at(mtx) ?: ' ') == c + 1 })
        }
        return count
    }

    override fun part1(): Any {
        val (mtx, trailHeads) = parseInput()
        return trailHeads.sumOf { bfs(mtx, it, false) }
    }

    override fun part2(): Any {
        val (mtx, trailHeads) = parseInput()
        return trailHeads.sumOf { bfs(mtx, it, true) }
    }
}

fun main() {
    Day10().run()
}