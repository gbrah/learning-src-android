
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
 fun welcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}

@Composable
 fun WelcomeScreen(navController: NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(10.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Quiz",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(all = 10.dp)
                        )
                        Text(
                            modifier = Modifier.padding(all = 10.dp),
                            text = "A simple Quiz to discovers KMP and compose.",
                        )
                        Button(
                            modifier = Modifier.padding(all = 10.dp),
                            onClick = { navController.navigate("quiz") }

                        ) {
                            Icon(
                                Icons.Filled.ArrowForward,
                                contentDescription = "Localized description",
                                Modifier.padding(end = 15.dp)
                            )
                            Text("Start the Quiz")
                        }
                    }
            }
        }
    }
}