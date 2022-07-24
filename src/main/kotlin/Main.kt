fun main() = DataRepository.run {
    val syntaxAnalyzer = SyntaxAnalyzer()
    val lexicalAnalyzer = LexicalAnalyzer()

    val inputString = dataInput()

    if (inputString == null) {
        dataOutput("REJECT")
        return
    }

    dataOutput(
        getResult(inputString, syntaxAnalyzer, lexicalAnalyzer)
    )
}

private fun getResult(
    expression: String,
    syntaxAnalyzer: SyntaxAnalyzer,
    lexicalAnalyzer: LexicalAnalyzer
): String = try {
    val transliterationResult = translate(expression)

    val lexicalAnalyzeResult = lexicalAnalyzer.analyze(transliterationResult)

    keywordCheck(lexicalAnalyzeResult)

    syntaxAnalyzer.analyze(lexicalAnalyzeResult)
} catch (e: IllegalStateException) {
    "REJECT"
}