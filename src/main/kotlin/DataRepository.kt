import java.io.File

object DataRepository {
    private const val rootPath = "./src/main/resources/"

    fun dataInput(): String? = File(rootPath + "input.txt")
        .bufferedReader()
        .readLine()

    fun dataOutput(res: String) = File(rootPath + "output.txt")
        .printWriter()
        .use { writer ->
            writer.print(res)
        }
}

object Constants {
    val pascalKeywords = setOf(
        "and", "array", "asm", "begin", "break", "case", "const", "constructor", "continue", "destructor",
        "div", "do", "downto", "else", "end", "false", "file", "for", "function", "goto", "if", "implementation",
        "inline", "interface", "label", "mod", "nil", "not", "object", "of", "on", "operator", "or", "packed",
        "procedure", "program", "record", "repeat", "set", "shl", "shr", "string", "then", "to", "true",
        "type", "unit", "until", "uses", "var", "while", "with", "xor",
    )

    val singleArithmeticSigns = setOf('+', '-', '*')

    val compositeArithmeticSigns = setOf("div", "mod")
}