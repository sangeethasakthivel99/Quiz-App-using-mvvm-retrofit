package com.sangeetha.quiz

import com.google.gson.annotations.SerializedName

data class QuizResponse(

	@field:SerializedName("response_code")
	val responseCode: Int? = null,

	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	val difficulty: String? = null,

	val question: String? = null,

	@field:SerializedName("correct_answer")
	val correctAnswer: String? = null,

	@field:SerializedName("incorrect_answers")
	val incorrectAnswers: List<String?>? = null,

	val category: String? = null,

	val type: String? = null
)
