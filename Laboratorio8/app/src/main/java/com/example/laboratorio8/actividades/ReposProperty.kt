package com.example.laboratorio8.actividades

import com.squareup.moshi.Json

data class ReposProperty(
    val name:String,
    @Json(name = "html_url")
    val repoSrcUrl: String

)