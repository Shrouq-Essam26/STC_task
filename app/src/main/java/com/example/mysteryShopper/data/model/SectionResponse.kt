package com.example.mysteryShopper.data.model

data class SectionResponse(
    val data: SectionData
)

data class SectionData(
    val results: List<SectionResult>
)

data class SectionResult(
    val id: Int,
    val title: String,          // For Comics/Series
    val name: String,           // For Stories/Events
    val description: String?,
    val thumbnail: Thumbnail?
)

