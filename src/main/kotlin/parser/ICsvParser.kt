package parser

interface ICsvParser<T> {
    fun parse(filename: String): List<T>
    fun parseLine(line: String): T
}