package util

class Point (var x: Int, var y: Int){
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)

    fun <T> isValidPosition(grid: List<List<T>>): Boolean {
        return 0 <= y && y < grid.size && 0 <= x && x < grid[y].size
    }

    fun <T> at (grid: List<List<T>>): T? {
        return if(isValidPosition(grid)) grid[y][x] else null
    }

    override fun equals(other: Any?): Boolean {
        if (other is Point) {
            return x == other.x && y == other.y
        }
        return false
    }

    override fun hashCode(): Int {
        return 256 * x + y
    }
}