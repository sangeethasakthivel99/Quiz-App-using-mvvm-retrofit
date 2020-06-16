package com.sangeetha.quiz

class QuizRepository {

    private val serviceProvider = ServiceProvider()

    val quizService by lazy {
        serviceProvider.provideQuizService()
    }

    fun getQuizQuestion(category: Int) = quizService.getQuizQuestion(amount = amount, category = category, difficulty = difficulty, type = type)
}