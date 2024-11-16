package com.example.mysteryShopper.data.model

import java.io.Serializable

data class CharacterResponse(
    val data: CharacterData
):Serializable

data class CharacterData(
    val results: List<CharacterModel>
):Serializable

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val resourceURI: String
):Serializable

data class Thumbnail(
    val path: String,
    val extension: String
):Serializable {
    val imageUrl: String
        get() = "$path.$extension"
}