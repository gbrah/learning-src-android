package com.example.quizappandroid.network

import com.example.quizappandroid.network.data.Answer
import com.example.quizappandroid.network.data.Question


class MockDataSource {

    suspend fun generateDummyQuestionsList():List<Question>{
        return generateQuestionsList()
    }
    fun generateQuestionsList():List<Question>{
        return listOf(
            Question(
                1,
                "Android is a great platform ?",
                1,
                listOf(
                    Answer( 1,"YES"),
                    Answer(2,"NO")
                )
            ),
            Question(
                1,
                "Android is a bad platform ?",
                2,
                listOf(
                    Answer( 1,"YES"),
                    Answer(2,"NO")
                )
            )
        )

    }

}