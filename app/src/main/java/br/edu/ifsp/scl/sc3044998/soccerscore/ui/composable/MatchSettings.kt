package br.edu.ifsp.scl.sc3044998.soccerscore.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3044998.soccerscore.ui.theme.SoccerScoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchSettings(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text("SoccerScore") })
    }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Nome do Time A")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Time A") },
                placeholder = { Text("Ex.: Barsemlona F.C., Real Madrugas...") },
                value = "",
                onValueChange = {})
            Text("Nome do Time B")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Time B") },
                placeholder = { Text("Ex.: Baile de Munique, Apend City...") },
                value = "",
                onValueChange = {})
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text("Gols do Time A")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Gols do Time A") },
                placeholder = { Text("Quantidade de gols...") },
                value = "",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {})
            Text("Gols do Time B")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Gols do Time B") },
                placeholder = { Text("Quantidade de gols...") },
                value = "",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {})
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                Text("Ver Resultado")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchSettingsPreview() {
    SoccerScoreTheme {
        MatchSettings()
    }
}