package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc3044998.soccerscore.config.Screen
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@Composable
fun MainNavigator(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MatchSettings.route
    ) {
        composable(
            route = Screen.MatchSettings.route
        ) {
            MatchSettings(navHostController = navHostController, modifier = modifier)
        }

        composable(
            route = Screen.MatchSummary.route
        ) {
            MatchSummary(navHostController = navHostController, modifier = modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNavigatorPreview() {
    SoccerScoreTheme() {
        MainNavigator(
            navHostController = rememberNavController(),
            modifier = Modifier
        )
    }
}