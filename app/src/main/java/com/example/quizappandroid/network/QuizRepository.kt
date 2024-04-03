package network

import android.content.Context
import com.example.quizappandroid.network.MockDataSource
import com.example.quizappandroid.network.QuizApiDatasource
import com.example.quizappandroid.network.QuizDbDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.quizappandroid.network.data.Question


class QuizRepository(context:Context)  {

    private val mockDataSource = MockDataSource()
    private val quizAPI = QuizApiDatasource()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var quizDB = QuizDbDataSource(context)

    private var _questionState=  MutableStateFlow(listOf<Question>())
    var questionState = _questionState

    init {
        updateQuiz()
    }

    private suspend fun fetchQuiz(): List<Question> = quizAPI.getAllQuestions().questions

    private suspend fun fetchAndStoreQuiz(): List<Question>{
        val questions  = fetchQuiz()
        quizDB.insertQuestions(questions)
        quizDB.setUpdateTimeStamp(System.currentTimeMillis()/1000)
        return questions
    }
    private fun updateQuiz(){


        coroutineScope.launch {
            _questionState.update {
                try {
                    val lastRequest = quizDB.getUpdateTimeStamp()
                    if(lastRequest == 0L || lastRequest - System.currentTimeMillis()/1000 > 300000){
                        fetchAndStoreQuiz()

                    }else{
                        quizDB.getAllQuestions()
                    }
                } catch (e: NullPointerException) {
                    fetchAndStoreQuiz()
                } catch (e: Exception) {
                    e.printStackTrace()
                    mockDataSource.generateDummyQuestionsList()
                }

            }
        }
    }
}