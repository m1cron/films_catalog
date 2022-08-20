package ru.micron.dto.omdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiSearchResponse(
    @JsonProperty("Search")
    var result: List<Search>? = null,
    @JsonProperty("totalResults")
    var totalAmount: String? = null,
    @JsonProperty("Response")
    var response: Boolean? = null
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    data class Search(
        @JsonProperty("Title")
        var title: String? = null,
        @JsonProperty("Year")
        var year: String? = null,
        @JsonProperty("imdbID")
        var imdbId: String? = null,
        @JsonProperty("Type")
        var type: String? = null,
        @JsonProperty("Poster")
        var posterUrl: String? = null
    )
}
