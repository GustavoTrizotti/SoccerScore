package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import br.edu.ifsp.scl.sc3044998.soccerscore.application.MatchSettingsDTO
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchSettings(
    onNextScreen: (MatchSettingsDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("SoccerScore - Match Settings") })
    }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            var teamA by rememberSaveable { mutableStateOf("") }
            var teamAError by rememberSaveable { mutableStateOf<String?>(null) }

            var scoreA by rememberSaveable { mutableStateOf("") }
            var scoreAError by rememberSaveable { mutableStateOf<String?>(null) }

            var teamB by rememberSaveable { mutableStateOf("") }
            var teamBError by rememberSaveable { mutableStateOf<String?>(null) }

            var scoreB by rememberSaveable { mutableStateOf("") }
            var scoreBError by rememberSaveable { mutableStateOf<String?>(null) }

            fun hasError(): Boolean {
                return teamAError != null || scoreAError != null || teamBError != null || scoreBError != null
            }

            fun checkResults() {
                teamAError = validateTeamName(teamA, "Time A")
                scoreAError = validateScore(scoreA, "Time A")
                teamBError = validateTeamName(teamB, "Time B")
                scoreBError = validateScore(scoreB, "Time B")

                if (hasError()) return
                val dto = MatchSettingsDTO(
                    teamA = teamA,
                    scoreA = scoreA.toInt(),
                    teamB = teamB,
                    scoreB = scoreB.toInt()
                )

                onNextScreen(dto)
            }

            Text("Nome do Time A")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = teamA,
                onValueChange = { teamA = it },
                isError = teamAError != null,
                supportingText = {
                    teamAError?.let { Text(it) }
                }
            )
            Text("Nome do Time B")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = teamB,
                isError = teamBError != null,
                supportingText = {
                    teamBError?.let { Text(it) }
                },
                onValueChange = { teamB = it }
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text("Gols do Time A")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = scoreA,
                isError = scoreAError != null,
                supportingText = {
                    scoreAError?.let { Text(it) }
                },
                onValueChange = { scoreA = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Text("Gols do Time B")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = scoreB,
                isError = scoreBError != null,
                supportingText = {
                    scoreBError?.let { Text(it) }
                },
                onValueChange = { scoreB = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = { checkResults() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Ver Resultado")
            }
        }
    }
}

fun validateTeamName(teamName: String, teamId: String): String? {
    if (teamName.isBlank())
        return "O nome do $teamId não pode ser vazio."
    if (teamName.isDigitsOnly())
        return "O nome do $teamId não pode conter apenas números."
    return null
}

fun validateScore(score: String, teamId: String): String? {
    if (score.isBlank())
        return "O número de gols do $teamId não pode ser vazio."
    if (!score.isDigitsOnly())
        return "O número de gols do $teamId deve conter apenas números."
    if (score.toIntOrNull() == null)
        return "O número de gols do $teamId deve ser um número válido."
    return null
}

@Preview(showBackground = true)
@Composable
fun MatchSettingsPreview() {
    SoccerScoreTheme {
        MatchSettings(onNextScreen = {})
    }
}