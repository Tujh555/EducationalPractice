class Lexem<T>(
     _lexemName: String,
    var lexemClass: T
) {
    var lexemName = _lexemName
        get() = field.lowercase()

    override fun toString() = "'$lexemName' - ${lexemClass.toString().lowercase()}"
}

enum class WordClass {
    IDENTIFIER,
    NUMBER,
    FIRST_STEP_ARITHMETIC_SIGN,
    SECOND_STEP_ARITHMETIC_SIGN,
    SPACE,
    SEMICOLON,
    SQUARE_CLOSED_BRACKET,
    CLOSED_BRACKET,
    OPENED_BRACKET,
    SQUARE_OPENED_BRACKET,
    IF_KEYWORD,
    THEN_KEYWORD,
    ELSE_KEYWORD,
}

enum class LetterClass {
    LETTER,
    SPACE,
    OPENED_BRACKET,
    SQUARE_OPENED_BRACKET,
    DIGIT,
    SQUARE_CLOSED_BRACKET,
    CLOSED_BRACKET,
    SEMICOLON,
    ARITHMETIC_SIGN,
}