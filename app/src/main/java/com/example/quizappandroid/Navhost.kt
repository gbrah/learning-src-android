import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import network.QuizRepository


private lateinit var quizRepository: QuizRepository // Assuming QuizRepository is defined elsewhere

@Composable
internal fun RootNavHost(context: Context){
    val navController = rememberNavController()
    quizRepository = QuizRepository(context) // Initialize quizRepository

    NavHost(navController, startDestination = "welcome") {
        composable(
            route = "welcome",
            // arguments = emptyList() // If there are no arguments
        ) {
            WelcomeScreen(navController)
        }
        composable(
            route = "quiz",
        ) {
            val questions = quizRepository.questionState.collectAsState()
            if (questions.value.isNotEmpty()) {
                QuestionScreen(navController,questions.value)
            }
        }
        composable(
            route = "score/{score}",
            arguments = listOf(navArgument("score") { type = NavType.StringType })
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")
            score?.let { ScoreScreen(navController,it) }
        }
    }
}
