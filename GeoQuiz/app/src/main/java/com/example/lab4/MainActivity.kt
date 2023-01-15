package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private var trueAnswers: Int = 0

    private val quizViewModel: QuizViewModel by
    lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quizViewModel.currentIndex = 0

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            hideButtons()
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            hideButtons()
        }
        nextButton.setOnClickListener {
            quizViewModel.nextQuestion()
            if(!quizViewModel.isEmptyQuestions()) {
                updateQuestion()
                showButtons()
            } else
                moveToSecondActivity()
        }
        updateQuestion()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle)
    {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("currentQuestionIndex", quizViewModel.currentIndex)
        savedInstanceState.putInt("visibleTrueButton", trueButton.visibility)
        savedInstanceState.putInt("visibleFalseButton", falseButton.visibility)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val currentIndex = savedInstanceState?.getInt("currentQuestionIndex", 0) ?: 0
        val trueButtonVisibility = savedInstanceState.getInt("visibleTrueButton", trueButton.visibility)
        val falseButtonVisibility = savedInstanceState.getInt("visibleFalseButton", falseButton.visibility)

        quizViewModel.currentIndex = currentIndex
        trueButton.visibility = trueButtonVisibility;
        falseButton.visibility = falseButtonVisibility;
    }

    private fun updateQuestion() {
        val questionText= quizViewModel.currentQuestionText
        questionTextView.setText(questionText)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correctToast
            trueAnswers += 1;
        } else {
            R.string.incorrectToast
        }
    }

    private fun hideButtons(){
        trueButton.visibility = View.INVISIBLE;
        falseButton.visibility = View.INVISIBLE;
    }

    private fun showButtons(){
        trueButton.visibility = View.VISIBLE;
        falseButton.visibility = View.VISIBLE;
    }

    private fun moveToSecondActivity(){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("trueAnswers", trueAnswers.toString())
        startActivity(intent)
    }


}