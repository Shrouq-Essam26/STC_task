package com.example.mysteryShopper.data.model

import java.io.Serializable

data class SectionModel(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnailUrl: String
):Serializable