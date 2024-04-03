package com.example.quizappandroid.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import com.example.quizappandroid.network.data.Quiz
import io.ktor.http.ContentType

class QuizApiDatasource {
    private val httpClient = HttpClient {
        engine {

        }
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Text.Plain, // because Github is not returning an 'application/json' header
                json = Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
        }
    }
    suspend fun getAllQuestions(): Quiz {
        return httpClient.get("https://raw.githubusercontent.com/worldline/learning-kotlin-multiplatform/main/quiz.json").body()
        //return httpClient.get("http://localhost:9080/quiz").body()
    }
}
