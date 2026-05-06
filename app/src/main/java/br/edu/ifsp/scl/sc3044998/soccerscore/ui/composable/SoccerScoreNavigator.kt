package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.edu.ifsp.scl.sc3044998.soccerscore.application.MatchDTO
import br.edu.ifsp.scl.sc3044998.soccerscore.application.Routes
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@Composable
fun MainNavigator(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val (onReset, setOnReset) = rememberSaveable { mutableStateOf<(() -> Unit)?>(null) }

    NavHost(
        navController = navHostController,
        startDestination = Routes.MatchSettings
    ) {
        composable<Routes.MatchSettings> {
            MatchSettings(
                modifier = modifier,
                setOnReset = setOnReset,
                onNextScreen = { dto ->
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
            val dto = MatchDTO(
                teamA = route.teamA,
                teamB = route.teamB,
                scoreA = route.scoreA,
                scoreB = route.scoreB
            )
            MatchSummary(
                modifier = modifier,
                dto = dto,
                onBackScreen = { navHostController.popBackStack() },
                onNextScreen = {
                    val result = getResults(dto)
                    navHostController.navigate(
                        Routes.MatchResults(result = result)
                    )
                }
            )
        }

        composable<Routes.MatchResults> { backStackEntry ->
            val route: Routes.MatchResults = backStackEntry.toRoute()
            val result = route.result
            MatchResult(
                modifier = modifier,
                result = result,
                onNavigate = {
                    onReset?.invoke()
                    navHostController.popBackStack(
                        route = Routes.MatchSettings,
                        inclusive = false
                    )
                }
            )

        }
    }
}

fun getResults(dto: MatchDTO): String {
    return if (dto.scoreA > dto.scoreB) {
        "${dto.teamA} venceu!"
    } else if (dto.scoreB > dto.scoreA) {
        "${dto.teamB} venceu!"
    } else {
        "Empate emocionante!"
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