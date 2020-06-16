package com.sangeetha.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private var totalScore: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        getDataFromBundle()

        setScore()

        btn_back.setOnClickListener {
            navigateToQuizHomeActivity()
        }

    }

    private fun setScore() {
        tv_score.text = totalScore.toString()
    }

    private fun getDataFromBundle() {
        intent?.extras?.let {
            totalScore = it.getInt(SCORE)
        }
    }

    private fun navigateToQuizHomeActivity() {
        val intent = Intent(this, QuizHomeActivity::class.java)
        startActivity(intent)
    }
}