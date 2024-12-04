package dev.programadorthi.advent.code

import println
import readInput

fun main() {
    val xComplement = "MAS"

    fun part1(input: List<String>): Int {
        var count = 0
        for (lineIndex in input.indices) {
            val line = input[lineIndex]
            var lastXIndex = 0
            var xIndex = 0
            while (xIndex >= 0 && xIndex < line.length) {
                xIndex = line.indexOf("X", startIndex = lastXIndex)
                if (xIndex == -1) {
                    break
                }
                lastXIndex = xIndex + 1

                var backward = ""
                var bottomText = ""
                var bottomLeftText = ""
                var bottomRightText = ""
                var forward = ""
                var topText = ""
                var topLeftText = ""
                var topRightText = ""
                for (i in 1..3) {
                    val backwardIndex = xIndex - i
                    val forwardIndex = xIndex + i

                    val nextLine = input.getOrElse(index = lineIndex + i, defaultValue = { "" })
                    val prevLine = input.getOrElse(index = lineIndex - i, defaultValue = { "" })

                    if (xIndex in 0..prevLine.lastIndex) {
                        topText += prevLine[xIndex]
                    }

                    if (xIndex in 0..nextLine.lastIndex) {
                        bottomText += nextLine[xIndex]
                    }

                    if (backwardIndex >= 0) {
                        backward += line[backwardIndex]
                        if (backwardIndex in 0..prevLine.lastIndex) {
                            topLeftText += prevLine[backwardIndex]
                        }
                        if (backwardIndex in 0..nextLine.lastIndex) {
                            bottomLeftText += nextLine[backwardIndex]
                        }
                    }

                    if (forwardIndex < line.length) {
                        forward += line[forwardIndex]
                        if (forwardIndex in 0..prevLine.lastIndex) {
                            topRightText += prevLine[forwardIndex]
                        }
                        if (forwardIndex in 0..nextLine.lastIndex) {
                            bottomRightText += nextLine[forwardIndex]
                        }
                    }
                }

                count += listOf(
                    backward, bottomText, bottomLeftText, bottomRightText,
                    forward, topText, topLeftText, topRightText
                ).count { it == xComplement }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (lineIndex in input.indices) {
            val line = input[lineIndex]
            var lastAIndex = 0
            var aIndex = 0
            while (aIndex >= 0 && aIndex < line.length) {
                aIndex = line.indexOf("A", startIndex = lastAIndex)
                if (aIndex == -1) {
                    break
                }
                lastAIndex = aIndex + 1

                if (lineIndex >= 1 && lineIndex < input.lastIndex) {
                    var topLeft = ""
                    var topRight = ""
                    var bottomLeft = ""
                    var bottomRight = ""

                    val nextLine = input[lineIndex + 1]
                    val prevLine = input[lineIndex - 1]

                    if (aIndex - 1 >= 0) {
                        topLeft += prevLine[aIndex - 1]
                        bottomLeft += nextLine[aIndex - 1]
                    }
                    if (aIndex + 1 < prevLine.length) {
                        topRight += prevLine[aIndex + 1]
                    }
                    if (aIndex + 1 < nextLine.length) {
                        bottomRight += nextLine[aIndex + 1]
                    }

                    if ((topLeft == "M" && bottomRight == "S" || topLeft == "S" && bottomRight == "M") &&
                        (topRight == "M" && bottomLeft == "S" || bottomLeft == "M" && topRight == "S")
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
