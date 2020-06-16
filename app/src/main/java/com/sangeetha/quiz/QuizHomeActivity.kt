package com.sangeetha.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_quiz_home.*

class QuizHomeActivity : AppCompatActivity() {

    private var quizType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_home)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        initClickListeners()
    }

    private fun initClickListeners() {
        ll_gk.setOnClickListener {
            quizType = GENERAL
            navigateToQuizQuestionActivity()
        }
        ll_animals.setOnClickListener {
            quizType = ANIMALS
            navigateToQuizQuestionActivity()
        }
        ll_movie.setOnClickListener {
            quizType = MOVIE
            navigateToQuizQuestionActivity()
        }
        ll_sports.setOnClickListener {
            quizType = SPORTS
            navigateToQuizQuestionActivity()
        }
    }

    private fun navigateToQuizQuestionActivity() {
        val intent = Intent(this, QuizQuestionActivity::class.java)
        intent.putExtra(QUIZ_TYPE,quizType)
        startActivity(intent)
    }
}