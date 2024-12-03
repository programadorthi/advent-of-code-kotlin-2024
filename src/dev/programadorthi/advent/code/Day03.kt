package dev.programadorthi.advent.code

import println
import readInput

fun main() {
    val regex = """mul\((\d+),(\d+)\)""".toRegex()

    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            result += regex
                .findAll(line)
                .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
                .sum()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var multiplying = true
        for (line in input) {
            var multiplyStart = 0
            var multiplyStop = 0
            while (true) {
                if (multiplying) {
                    multiplyStop = line.indexOf("don't()", startIndex = multiplyStart)
                    val content = when (multiplyStop) {
                        -1 -> line.substring(startIndex = multiplyStart)
                        else -> line.substring(startIndex = multiplyStart, endIndex = multiplyStop)
                    }
                    result += regex
                        .findAll(content)
                        .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
                        .sum()
                    multiplying = multiplyStop == -1
                }

                multiplyStart = line.indexOf("do()", startIndex = multiplyStop)
                multiplying = multiplying || multiplyStart > -1
                if (multiplyStart == -1 || multiplyStop == -1) {
                    break
                }
            }
        }
        return result
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
