package util

import java.io.File

fun readFile(year: Int, day: Int): String {
    return File("input/y$year/day${day.toString().padStart(2, '0')}.txt").readText()
}