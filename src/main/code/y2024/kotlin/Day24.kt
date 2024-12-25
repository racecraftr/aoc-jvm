package y2024.kotlin

import day.Day
import java.util.LinkedList
import java.util.Queue

class Day24 : Day(2024, 24) {

    enum class Operator {
        AND, OR, XOR
    }

    inner class Gate(val op1: String, val operator: Operator, val op2: String, val out: String) {
        constructor(line: List<String>) : this(line[0], Operator.valueOf(line[1]), line[2], line[3])

        fun isOnXY(): Boolean {
            return (op1.first() == 'x' || op1.first() == 'y')
                    && (op2.first() == 'x' || op2.first() == 'y')
        }

        fun isNotOnXY(): Boolean {
            return !(op1.first() == 'x' || op1.first() == 'y')
                    && !(op2.first() == 'x' || op2.first() == 'y')
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other is Gate) {
                return op1 == other.op1 && operator == other.operator && op2 == other.op2 && out == other.out
            }
            return false
        }

        override fun hashCode(): Int {
            var result = op1.hashCode()
            result = 31 * result + operator.hashCode()
            result = 31 * result + op2.hashCode()
            result = 31 * result + out.hashCode()
            return result
        }
    }

    fun parseInput(): Pair<MutableMap<String, Int>, List<Gate>> {
        val parts = input.split("\n\n")
        val initialWires = parts[0].split("\n")
            .map { it.split(": ") }
            .map { it[0] to it[1].toInt() }.toMap()

        val instructions = parts[1].lines()
            .map { it.split(" ") }
            .map { Gate(listOf(it[0], it[1], it[2], it[4])) }

        return Pair(initialWires.toMutableMap(), instructions)
    }

    override fun part1(): Any {
        val (wires, instructions) = parseInput()
        val instructionQueue: Queue<Gate> = LinkedList()
        instructionQueue.addAll(instructions)

        while (instructionQueue.isNotEmpty()) {
            val instruction = instructionQueue.poll()
            val w1 = instruction.op1
            val w2 = instruction.op2
            if (wires[w1] == null || wires[w2] == null) {
                instructionQueue.add(instruction)
            } else wires.put(
                instruction.out, when (instruction.operator) {
                    Operator.AND -> wires[w1]!! and wires[w2]!!
                    Operator.OR -> wires[w1]!! or wires[w2]!!
                    Operator.XOR -> wires[w1]!! xor wires[w2]!!
                }
            )
        }
        return wires.entries.filter {it.key.first() == 'z'}.sortedBy { it.key }.map { it.value }
            .withIndex().sumOf { it.value.toLong() shl it.index } // hehe
    }

    override fun part2(): Any {
        val (_, instructions) = parseInput()
        val finalBit = instructions.map { it.out }.sorted().reversed().first()
        val faulty = mutableListOf<Gate>()

        for (gate in instructions) {
            if (gate.out.first() == 'z' && gate.out != finalBit) {
                if (gate.operator != Operator.XOR) {
                    faulty.add(gate)
                }
            } else if (gate.out.first() != 'z' && gate.isNotOnXY()) {
                if (gate.operator == Operator.XOR) {
                    faulty.add(gate)
                }
            } else if (gate.operator == Operator.XOR && gate.isOnXY()) {
                val out = gate.out
                val anotherFound = instructions.filter {
                        (it.op1 == out || it.op2 == out) && it.operator == Operator.XOR
                    }.isNotEmpty()
                if (!anotherFound) {
                    faulty.add(gate)
                }
            } else if (gate.operator == Operator.AND && gate.isOnXY()) {
                if (!(gate.op1.endsWith("00") && gate.op2.endsWith("00"))) {
                    val out = gate.out
                    val anotherFound = instructions.filter {
                            (it.op1 == out || it.op2 == out) && it.operator == Operator.OR
                        }.isNotEmpty()
                    if (!anotherFound) {
                        faulty.add(gate)
                    }
                }
            }
        }
       return faulty.map { it.out }.sorted().joinToString(",")
    }
}

fun main() {
    Day24().run()
}