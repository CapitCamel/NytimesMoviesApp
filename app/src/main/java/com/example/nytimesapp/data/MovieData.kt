package com.example.nytimesapp.data

data class Results(
    val display_title: String,
    val multimedia: Multimedia,
    val summary_short: String
)

data class Multimedia(
    val src : String
)

data class MovieData(
    val results: List<Results>
)


