package com.example.quizappandroid.network

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.quizappandroid.network.data.Answer
import com.example.quizappandroid.network.data.Question
import com.myapplication.common.cache.Database
import kotlinx.coroutines.CoroutineScope


class QuizDbDataSource(context:Context) {
    private  var sqlDriver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "quiz.db",
        callback = object : AndroidSqliteDriver.Callback(Database.Schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                db.setForeignKeyConstraintsEnabled(true)
            }
        }
    )
    private  var database= Database(sqlDriver)
    private  var quizQueries=database.quizDatabaseQueries


    suspend fun getUpdateTimeStamp():Long = quizQueries.selectUpdateTimestamp().executeAsOneOrNull()?.timestamprequest ?: 0L


    suspend fun setUpdateTimeStamp(timeStamp:Long)  {
        quizQueries.deleteTimeStamp()
        quizQueries.insertTimeStamp(timeStamp)
    }

    suspend fun getAllQuestions(): List<Question> {
        return quizQueries.selectAllQuestionsWithAnswers().executeAsList()
            .groupBy {it.question_id }
            .map { (questionId, rowList) ->

                Question(
                    id = questionId,
                    label = rowList.first().label,
                    correctAnswerId = rowList.first().correctAnswerId,
                    answers = rowList.map { answer ->
                        Answer(
                            id = answer.id_,
                            label = answer.label_
                        )
                    }
                )
            }
    }



    suspend fun insertQuestions(questions:List<Question>) {
        quizQueries.deleteQuestions();
        quizQueries.deleteAnswers()
        questions.forEach {question ->
            quizQueries.insertQuestion(question.id, question.label, question.correctAnswerId)
            question.answers.forEach {answer ->
                quizQueries.insertAnswer(answer.id,answer.label,question.id)
            }
        }
    }
}
