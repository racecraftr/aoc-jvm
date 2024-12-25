package y2024.kotlin

import day.Day
import java.util.HashMap

class Day23: Day(2024, 23) {

    private fun parseInput(): HashMap<String, Set<String>> {
        val graph = HashMap<String, Set<String>>()
        for (line in input.lines()) {
            val (a, b) = line.split("-")
            graph[a] = (graph[a] ?: HashSet()).plus(b)
            graph[b] = (graph[b] ?: HashSet()).plus(a)
        }
        return graph
    }

    fun bronKerbosch(graph: Map<String, Set<String>>,
                     r: MutableSet<String>, p: MutableSet<String>, x: MutableSet<String>,
                     maxLenClique: MutableList<String>) {
        if (p.isEmpty() && x.isEmpty() && r.size > maxLenClique.size) {
            maxLenClique.clear()
            maxLenClique.addAll(r)
            return
        }
        for (v in p.toSet()) {
            val newR = r.toMutableSet()
            newR.add(v)

            val newP = p.toMutableSet()
            newP.retainAll(graph[v] ?: emptySet())

            val newX = x.toMutableSet()
            newX.retainAll(graph[v] ?: emptySet())

            bronKerbosch(graph, newR, newP, newX, maxLenClique)

            p -= v
            x += v
        }
    }

    override fun part1(): Any {
        val graph = parseInput()
        val cliques = HashSet<String>()
        for (n1 in graph.keys) {
            if (n1.first() != 't') {
                continue
            }
            for (n2 in graph[n1]!!) {
                for (n3 in graph[n2]!!) {
                    if (graph[n3]!!.contains(n1)) {
                        val clique = listOf(n1, n2, n3).sorted()
                        cliques += clique.joinToString("")
                    }
                }
            }
        }
        return cliques.size
    }



    override fun part2(): Any {
        val graph = parseInput()
        var maxLenClique = ArrayList<String>()
        bronKerbosch(graph, HashSet<String>().toMutableSet(),
            graph.keys.toHashSet().toMutableSet(),
            HashSet<String>().toMutableSet(), maxLenClique)

        return maxLenClique.sorted().joinToString(",")
    }

}

fun main() {
    Day23().run()
}