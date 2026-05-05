package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3044998.soccerscore.application.MatchSettingsDTO
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchSummary(
    modifier: Modifier = Modifier,
    dto: MatchSettingsDTO? = null,
    onBackScreen: () -> Unit,
    onNextScreen: () -> Unit
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("SoccerScore - Match Summary") })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(dto?.teamA ?: "?", fontSize = 20.sp)
                    Text(dto?.scoreA.toString(), fontSize = 72.sp)
                }
                Text("X", fontSize = 24.sp)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(dto?.teamB ?: "?", fontSize = 20.sp)
                    Text(dto?.scoreB.toString(), fontSize = 72.sp)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(modifier = Modifier.weight(2f), onClick = { onNextScreen() }) {
                    Text("Confirmar Resultado", textAlign = TextAlign.Center)
                }
                FilledTonalButton(modifier = Modifier.weight(1f), onClick = { onBackScreen() }) {
                    Text("Editar", textAlign = TextAlign.Center)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MatchSummaryPreview() {
    SoccerScoreTheme {
        MatchSummary(onBackScreen = {}, onNextScreen = {})
    }
}