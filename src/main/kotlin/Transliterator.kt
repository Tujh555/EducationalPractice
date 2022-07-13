import Constants.singleArithmeticSigns

private fun getClass(item: Char) = when (item) {
    in 'a'..'z' -> LetterClass.LETTER
    in 'A'..'Z' -> LetterClass.LETTER
    in '0'..'9' -> LetterClass.DIGIT
    in singleArithmeticSigns -> LetterClass.ARITHMETIC_SIGN
    ';' -> LetterClass.SEMICOLON
    '[' -> LetterClass.SQUARE_OPENED_BRACKET
    ']' -> LetterClass.SQUARE_CLOSED_BRACKET
    '(' -> LetterClass.OPENED_BRACKET
    ')' -> LetterClass.CLOSED_BRACKET
    ' ' -> LetterClass.SPACE
    else -> throw IllegalStateException()
}

fun translate(data: String) = data.map { currentChar ->
    Lexem(currentChar.toString(), getClass(currentChar))
}