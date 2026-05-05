package br.edu.ifsp.scl.sc3044998.soccerscore.application

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MatchSettings : Routes()

    @Serializable
    data class MatchSummary(
        val teamA: String,
        val scoreA: Int,
        val teamB: String,
        val scoreB: Int
    ) : Routes()
}