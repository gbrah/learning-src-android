import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
internal fun ScoreScreenPreview() {
    ScoreScreen(rememberNavController(),score = "10/10")
}

@Composable
internal fun ScoreScreen(navController: NavHostController, score: String){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(10.dp),
            //backgroundColor = Color.Green,

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            fontSize = 15.sp,
                            text = "score",
                        )
                        Text(
                            fontSize = 30.sp,
                            text = score,
                        )
                        Button(
                            modifier = Modifier.padding(all = 20.dp),
                            onClick = {
                                navController.navigate(route = "quiz")
                            }
                        ) {
                            Icon(Icons.Filled.Refresh, contentDescription = "Localized description")
                            Text(text = "Retake the Quiz",)

                        }
                    }
            }
        }
    }
}