package util

class Point (var x: Int, var y: Int){
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)

    operator fun rem(bound: Point) = Point(x.mod(bound.x), y.mod(bound.y))

    operator fun times(i: Int) = Point(x * i, y * i)

    operator fun div(i: Int) = Point(x / i, y / i)

    operator fun component1() = x
    operator fun component2() = y

    fun <T> isValidPosition(grid: List<List<T>>): Boolean {
        return 0 <= y && y < grid.size && 0 <= x && x < grid[y].size
    }

    fun <T> at (grid: List<List<T>>): T? {
        return if(isValidPosition(grid)) grid[y][x] else null
    }

    fun <T> atOrDefault(grid: List<List<T>>, defaultValue: T): T {
        return if(isValidPosition(grid)) grid[y][x] else defaultValue
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

    override fun toString(): String {
        return "Point($x, $y)"
    }
}