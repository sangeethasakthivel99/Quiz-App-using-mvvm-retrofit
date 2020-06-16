package com.sangeetha.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {

    val quizRepository = QuizRepository()

    fun getQuizQuestion(category: Int): LiveData<ApiResponse<QuizResponse>> {
        return quizRepository.getQuizQuestion(category)
    }
}