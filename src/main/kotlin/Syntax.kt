import WordClass.*

class SyntaxAnalyzer {
    private var state = AnalyzeState.START
    private val states: Map<AnalyzeState, Map<WordClass, AnalyzeState>>

    init {
        val lexemClassMapList = listOf(
            mapOf(IF_KEYWORD to AnalyzeState.IF),
            mapOf(IDENTIFIER to AnalyzeState.CALL_1),
            mapOf(OPENED_BRACKET to AnalyzeState.LIST_PARAMS),
            mapOf(IDENTIFIER to AnalyzeState.ELEMENT_1),
            mapOf(SQUARE_OPENED_BRACKET to AnalyzeState.INDEX_1),
            mapOf(NUMBER to AnalyzeState.NUMBER_1, FIRST_STEP_ARITHMETIC_SIGN to AnalyzeState.PREPARE_NUMBER_1),
            mapOf(NUMBER to AnalyzeState.NUMBER_1),
            mapOf(SQUARE_CLOSED_BRACKET to AnalyzeState.ELEMENT_2),
            mapOf(FIRST_STEP_ARITHMETIC_SIGN to AnalyzeState.SIGN, SECOND_STEP_ARITHMETIC_SIGN to AnalyzeState.SIGN),
            mapOf(IDENTIFIER to AnalyzeState.ELEMENT_3),
            mapOf(SQUARE_OPENED_BRACKET to AnalyzeState.INDEX_2),
            mapOf(NUMBER to AnalyzeState.NUMBER_2, FIRST_STEP_ARITHMETIC_SIGN to AnalyzeState.PREPARE_NUMBER_2),
            mapOf(NUMBER to AnalyzeState.NUMBER_2),
            mapOf(SQUARE_CLOSED_BRACKET to AnalyzeState.ELEMENT_4),
            mapOf(CLOSED_BRACKET to AnalyzeState.CLOSE_BRACKET_1),
            mapOf(THEN_KEYWORD to AnalyzeState.THEN),
            mapOf(IDENTIFIER to AnalyzeState.CALL_2),
            mapOf(OPENED_BRACKET to AnalyzeState.OPEN_BRACKET_1),
            mapOf(IDENTIFIER to AnalyzeState.IDENTIFIER_1),
            mapOf(CLOSED_BRACKET to AnalyzeState.CLOSE_BRACKET_2),
            mapOf(SEMICOLON to AnalyzeState.SEMICOLON, ELSE_KEYWORD to AnalyzeState.ELSE),
            mapOf(IDENTIFIER to AnalyzeState.CALL_3),
            mapOf(OPENED_BRACKET to AnalyzeState.OPEN_BRACKET_2),
            mapOf(IDENTIFIER to AnalyzeState.IDENTIFIER_2),
            mapOf(CLOSED_BRACKET to AnalyzeState.CLOSE_BRACKET_3),
            mapOf(SEMICOLON to AnalyzeState.SEMICOLON),
        )

        states = AnalyzeState.values().zip(lexemClassMapList).toMap()
    }

    private fun getState(wordClass: WordClass) = states[state]
        ?.get(wordClass)
        ?: throw IllegalArgumentException()

    fun analyze(words: List<Lexem<WordClass>>): String {
        state = AnalyzeState.START

        for (lexem in words) {
            if (lexem.lexemClass == SPACE)
                continue

            try {
                state = getState(lexem.lexemClass)
            } catch (e: IllegalArgumentException) {
                return "REJECT"
            }
        }

        return if (state == AnalyzeState.SEMICOLON) "ACCEPT" else "REJECT"
    }

    private enum class AnalyzeState {
        START,
        IF,
        CALL_1,
        LIST_PARAMS,
        ELEMENT_1,
        INDEX_1,
        PREPARE_NUMBER_1,
        NUMBER_1,
        ELEMENT_2,
        SIGN,
        ELEMENT_3,
        INDEX_2,
        PREPARE_NUMBER_2,
        NUMBER_2,
        ELEMENT_4,
        CLOSE_BRACKET_1,
        THEN,
        CALL_2,
        OPEN_BRACKET_1,
        IDENTIFIER_1,
        CLOSE_BRACKET_2,
        ELSE,
        CALL_3,
        OPEN_BRACKET_2,
        IDENTIFIER_2,
        CLOSE_BRACKET_3,
        SEMICOLON,
    }
}