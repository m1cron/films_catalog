package ru.micron.dto.omdb

import com.fasterxml.jackson.annotation.JsonProperty

data class RatingsDto(
    @JsonProperty("Source")
    var source: String? = null,
    @JsonProperty("Value")
    var value: String? = null
)
