package com.sangeetha.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_quiz_question.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val quizViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(QuizViewModel::class.java)
    }

    private var mCurrentPosition: Int = 1

    private var mSelectedPosition: Int = 0

    private var mSelectedOption: TextView? = null

    private var category: Int = 0

    private var mTotalCorrectAnswers: Int = 0

    private var questionList: List<ResultsItem?>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        chronometer_timer.start()

        getDataFromBundle()

        getQuizQuestions()
    }

    private fun getDataFromBundle() {
        intent.extras.let {
            val quizType = it?.getString(QUIZ_TYPE)
            category = when (quizType) {
                GENERAL -> 9
                MOVIE -> 11
                ANIMALS -> 27
                SPORTS -> 21
                else -> 0
            }
        }
    }

    private fun getQuizQuestions() {
        quizViewModel.getQuizQuestion(category).observe(this, Observer {
            if (it?.shouldShowRetryButton == false) {
                if (it.body?.responseCode == SUCCESS_CODE) {
                    it.body?.results.let {
                        questionList = it
                        setDataInView()
                    }
                }
            }
        })
    }

    private fun setDataInView() {
        progress_bar.progress = mCurrentPosition
        ll_loading.visibility = View.GONE
        sv_question_container.visibility = View.VISIBLE
        setOptionsTextView()
        initClickListeners()
        tv_progress_question.text = "${mCurrentPosition} / ${progress_bar.max}"
        tv_question.text = questionList?.get(mCurrentPosition - 1)?.question
        val wrongOption = questionList?.get(mCurrentPosition -1)?.incorrectAnswers
        tv_option1.text = wrongOption?.get(0) ?: "Default"
        tv_option2.text = wrongOption?.get(1) ?: "Default"
        tv_option3.text = questionList?.get(mCurrentPosition - 1)?.correctAnswer
        tv_option4.text = wrongOption?.get(2) ?: "Default"
    }

    private fun setOptionsTextView() {
        val options = ArrayList<TextView>()
        options.add(0,tv_option1)
        options.add(1,tv_option2)
        options.add(2,tv_option3)
        options.add(3,tv_option4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#252424"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.background_option)

        }
    }

    private fun initClickListeners() {
        tv_option1.setOnClickListener(this)
        tv_option2.setOnClickListener(this)
        tv_option3.setOnClickListener(this)
        tv_option4.setOnClickListener(this)

        btn_next.setOnClickListener {
            if (mSelectedPosition == 0) {
                mCurrentPosition++
                when {
                    mCurrentPosition <= questionList!!.size -> {
                        setDataInView()
                    }
                    else -> {
                        chronometer_timer.stop()
                        val intent = Intent(this, ScoreActivity::class.java)
                        intent.putExtra(SCORE,mTotalCorrectAnswers)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                val question = questionList?.get(mCurrentPosition - 1)
                if (question?.correctAnswer?.equals(mSelectedOption?.text) == false) {
                    setAnswerView(mSelectedPosition, R.drawable.background_wrong_option)
                } else {
                    mTotalCorrectAnswers++
                }
                setAnswerView(3,R.drawable.background_correct_option)

                if (mCurrentPosition == questionList?.size) {
                    btn_next.text = getString(R.string.finish)
                }

                mSelectedPosition = 0
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option1 -> {
                selectedOptionView(tv_option1, 1)
            }
            R.id.tv_option2 -> {
                selectedOptionView(tv_option2, 2)
            }
            R.id.tv_option3 -> {
                selectedOptionView(tv_option3, 3)
            }
            R.id.tv_option4 -> {
                selectedOptionView(tv_option4, 4)
            }
        }
    }

    private fun setAnswerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option1.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 -> {
                tv_option2.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 -> {
                tv_option3.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 -> {
                tv_option4.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }

    private fun selectedOptionView(textView: TextView, selectedOption: Int) {
        mSelectedPosition = selectedOption
        mSelectedOption = textView
        setOptionsTextView()
        textView.setTextColor(Color.parseColor("#000000"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.background_selected_option)
    }
}