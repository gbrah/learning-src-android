package com.example.quizappandroid.network.data

import kotlinx.serialization.Serializable


@Serializable
data class Quiz(var questions: List<Question>)