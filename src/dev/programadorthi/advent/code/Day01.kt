package dev.programadorthi.advent.code

import println
import readInput
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach {
            val content = it.split(" ")
            left += content.first().toInt()
            right += content.last().toInt()
        }
        left.sort()
        right.sort()
        return left.zip(right).sumOf { pair -> abs(pair.first - pair.second) }
    }

    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.forEach {
            val content = it.split(" ")
            left += content.first().toInt()
            right += content.last().toInt()
        }
        val counted = mutableMapOf<Int, Int>()
        for (l in left) {
            if (counted.contains(l)) {
                continue
            }
            counted[l] = right.count { it == l }
        }
        return left.sumOf { (counted[it] ?: 0) * it }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
