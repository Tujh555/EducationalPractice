import Constants.compositeArithmeticSigns
import Constants.pascalKeywords

fun keywordCheck(words: List<Lexem<WordClass>>) {
    words.forEach { lexem ->
        when (lexem.lexemName) {
            "if" -> lexem.lexemClass = WordClass.IF_KEYWORD
            "then" -> lexem.lexemClass = WordClass.THEN_KEYWORD
            "else" -> lexem.lexemClass = WordClass.ELSE_KEYWORD
            in (compositeArithmeticSigns + "*") -> lexem.lexemClass = WordClass.SECOND_STEP_ARITHMETIC_SIGN
            in pascalKeywords -> throw IllegalStateException()
        }
    }
}