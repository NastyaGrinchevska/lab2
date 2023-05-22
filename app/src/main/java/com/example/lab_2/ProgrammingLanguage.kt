package com.example.lab_2

import java.io.Serializable

data class ProgrammingLanguage(
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val url: String
): Serializable