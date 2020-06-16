package com.sangeetha.quiz

class ServiceProvider {

    fun provideQuizService(): QuizService = ApiClient.getNetworkClient().create(QuizService::class.java)
}