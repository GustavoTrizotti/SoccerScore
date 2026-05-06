package br.edu.ifsp.scl.sc3044998.soccerscore.application

import kotlinx.serialization.Serializable

@Serializable
data class MatchDTO(
    val teamA: String,
    val scoreA: Int,
    val teamB: String,
    val scoreB: Int
)
