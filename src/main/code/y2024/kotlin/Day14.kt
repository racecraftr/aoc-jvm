package y2024.kotlin

import day.Day
import util.Point

class Day14: Day(2024, 14) {

    val maxPt = Point(101, 103)

    inner class Robot(var p: Point, val v: Point) {
        fun update() {
            p = (p + v) % maxPt
        }

        fun update(steps: Int) {
            p = (p + v * steps) % maxPt
        }

        override fun toString(): String {
            return "Robot($p, $v)"
        }
    }

    private fun parseInput(): List<Robot> {
        return input.lines().map { ln -> Regex("-?\\d+").findAll(ln).map { it.value }.toList().run {
            val (px, py, vx, vy) = this.map { it.toInt() }
            Robot(Point(px, py), Point(vx, vy))
        } }
    }

    override fun part1(): Any {
        val (midX, midY) = (maxPt / 2)
        val robots = parseInput()
        val quadrantCount = LongArray(4)
        for (robot in robots) {
            robot.update(100)
            println(robot)
            if (robot.p.x == midX || robot.p.y == midY) {
                continue
            }
            if (robot.p.x < midX && robot.p.y < midY) {
                quadrantCount[0] ++
            }
            if (robot.p.x < midX && robot.p.y > midY) {
                quadrantCount[1] ++
            }
            if (robot.p.x > midX && robot.p.y < midY) {
                quadrantCount[2] ++
            }
            if (robot.p.x > midX && robot.p.y > midY) {
                quadrantCount[3] ++
            }
        }
        return quadrantCount.reduce { a, b -> a * b }
    }

    override fun part2(): Any {
        val robots = parseInput()
        var seconds = 0
        while (true) {
            robots.forEach {it.update()}
            seconds++
            if (robots.map {it.p}.toSet().size == robots.size) break
        }
        return seconds
    }

}

fun main() {
    Day14().run()
}