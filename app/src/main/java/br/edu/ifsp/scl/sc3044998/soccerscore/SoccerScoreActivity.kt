package br.edu.ifsp.scl.sc3044998.soccerscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable.MainNavigator
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

class SoccerScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoccerScoreTheme {
                val mainNavHostController = rememberNavController()
                MainNavigator(
                    navHostController = mainNavHostController
                )
            }
        }
    }
}