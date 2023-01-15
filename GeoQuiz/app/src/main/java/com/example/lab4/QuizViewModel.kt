package com.example.lab4

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentIndex = 0

    private val questionBank = listOf(
        TrueFalse(R.string.questionAustralia, true),
        TrueFalse(R.string.questionOceans, true),
        TrueFalse(R.string.questionMideast, false),
        TrueFalse(R.string.questionAfrica, false),
        TrueFalse(R.string.questionAmericas, true),
        TrueFalse(R.string.questionAsia, true))

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].questionText

    fun nextQuestion() {
        currentIndex += 1
    }

    fun isEmptyQuestions() : Boolean {
        return currentIndex == questionBank.size
    }
}
