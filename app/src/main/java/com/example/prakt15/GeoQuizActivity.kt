package com.example.prakt15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.view.Gravity
import android.widget.TextView



class GeoQuizActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var pastButton: Button
    private lateinit var questText: TextView
    private val questionBank = listOf(
        Question(R.string.qes_text1, true),
        Question(R.string.qes_text2, false),
        Question(R.string.qes_text3, true),
        Question(R.string.qes_text4, true),
        Question(R.string.qes_text5, true))
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_quiz)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.nextbut)
        pastButton = findViewById(R.id.pastbut)
        questText = findViewById(R.id.textQues0)
        trueButton.setOnClickListener { view: View -> checkAnswer(true)
            if ( currentIndex != 4) {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()}
        }
        falseButton.setOnClickListener { view: View -> checkAnswer(false)
            if ( currentIndex != 4) {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()}
        }
        val questTextResId = questionBank[currentIndex].textResId
        questText.setText(questTextResId)
        nextButton.setOnClickListener {
            if ( currentIndex != 4) {
                currentIndex = (currentIndex + 1) % questionBank.size
                updateQuestion()}
            else
                Toast.makeText(this, R.string.evil_nextbut, Toast.LENGTH_SHORT).show()}
        pastButton.setOnClickListener {
            if ( currentIndex != 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()}
            else
                Toast.makeText(this, R.string.evil_pastbut, Toast.LENGTH_SHORT).show()}
        updateQuestion()
    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questText.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        }
        else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}