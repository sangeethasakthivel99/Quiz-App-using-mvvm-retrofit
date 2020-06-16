package com.sangeetha.quiz

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {

    @GET("api.php/")
    fun getQuizQuestion(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): LiveData<ApiResponse<QuizResponse>>

}