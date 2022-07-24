import LetterClass.*

class LexicalAnalyzer {
    private val lexicalAnalyzeResult = mutableListOf<Lexem<WordClass>>()
    private val states: Map<LetterClass, StateProvider>
    private val lastElementIndex: Int
        get() = lexicalAnalyzeResult.size - 1
    private var state = AnalyzeState.START

    init {
        val providers = listOf(
            StateProvider(AnalyzeState.IDENTIFIER, WordClass.IDENTIFIER),
            StateProvider(AnalyzeState.START, WordClass.IDENTIFIER),
            StateProvider(AnalyzeState.START, WordClass.OPENED_BRACKET),
            StateProvider(AnalyzeState.START, WordClass.SQUARE_OPENED_BRACKET),
            StateProvider(AnalyzeState.NUMBER, WordClass.NUMBER) { state == AnalyzeState.START },
            StateProvider(AnalyzeState.START, WordClass.SQUARE_CLOSED_BRACKET),
            StateProvider(AnalyzeState.START, WordClass.CLOSED_BRACKET),
            StateProvider(AnalyzeState.START, WordClass.SEMICOLON),
            StateProvider(AnalyzeState.START, WordClass.FIRST_STEP_ARITHMETIC_SIGN),
        )

        states = LetterClass.values().zip(providers).toMap()
    }

    private fun createLexem(lexem: Lexem<LetterClass>, wordClass: WordClass) {
        val addLexem: () -> Unit = {
            if (lexem.lexemClass != SPACE) {
                lexicalAnalyzeResult.add(
                    Lexem(
                        lexem.lexemName,
                        wordClass
                    )
                )
            }
        }

        when (state) {
            AnalyzeState.START -> {
                addLexem()
            }
            AnalyzeState.IDENTIFIER -> {
                if (lexem.lexemClass == LETTER || lexem.lexemClass == DIGIT) {
                    lexicalAnalyzeResult[lastElementIndex].lexemName += lexem.lexemName
                    return
                }

                addLexem()
            }
            AnalyzeState.NUMBER -> {
                if (lexem.lexemClass == DIGIT) {
                    lexicalAnalyzeResult[lastElementIndex].lexemName += lexem.lexemName
                    return
                }

                if (lexem.lexemClass == LETTER) {
                    throw IllegalStateException()
                }

                addLexem()
            }
        }
    }

    private fun getStatements(lexemClass: LetterClass) = states[lexemClass]
        ?.getStates()
        ?: throw IllegalStateException()

    fun analyze(symbols: List<Lexem<LetterClass>>): List<Lexem<WordClass>> {
        state = AnalyzeState.START
        lexicalAnalyzeResult.clear()

        symbols.forEach { symbolLexem ->
            val currentStates = getStatements(symbolLexem.lexemClass)
            createLexem(symbolLexem, currentStates.second)
            state = currentStates.first
        }

        return lexicalAnalyzeResult
    }

    private inner class StateProvider(
        private val newState: AnalyzeState,
        private val newWordClass: WordClass,
        private val issuanceCondition: () -> Boolean = { true }
    ) {
        fun getStates() = if (issuanceCondition()) {
            Pair(newState, newWordClass)
        } else {
            Pair(state, newWordClass)
        }
    }

    private enum class AnalyzeState {
        START, IDENTIFIER, NUMBER
    }
}