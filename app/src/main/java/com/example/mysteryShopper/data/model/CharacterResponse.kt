package com.example.mysteryShopper.data.model

data class CharacterResponse(
    val data: CharacterData
)

data class CharacterData(
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
) {
    val imageUrl: String
        get() = "$path.$extension"
}