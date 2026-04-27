package br.edu.ifsp.scl.sc3044998.soccerscore.config

sealed class Screen(val route: String) {
    object MatchSettings : Screen("match_settings_screen");
    object MatchSummary : Screen("match_summary_screen");
    object MatchResult : Screen("match_result_screen");
}