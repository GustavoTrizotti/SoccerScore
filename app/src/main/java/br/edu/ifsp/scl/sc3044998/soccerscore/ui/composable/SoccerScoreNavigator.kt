package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.edu.ifsp.scl.sc3044998.soccerscore.application.MatchSettingsDTO
import br.edu.ifsp.scl.sc3044998.soccerscore.application.Routes
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@Composable
fun MainNavigator(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.MatchSettings
    ) {
        composable<Routes.MatchSettings> {
            MatchSettings(modifier = modifier, onNextScreen = { dto ->
                navHostController.navigate(
                    Routes.MatchSummary(
                        teamA = dto.teamA,
                        teamB = dto.teamB,
                        scoreA = dto.scoreA,
                        scoreB = dto.scoreB
                    )
                )
            })
        }

        composable<Routes.MatchSummary> { backStackEntry ->
            val route: Routes.MatchSummary = backStackEntry.toRoute()
            val dto = MatchSettingsDTO(
                teamA = route.teamA,
                teamB = route.teamB,
                scoreA = route.scoreA,
                scoreB = route.scoreB
            )
            MatchSummary(
                modifier = modifier,
                dto = dto,
                onBackScreen = { navHostController.popBackStack() },
                onNextScreen = {}
            )
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