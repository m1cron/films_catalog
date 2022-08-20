package ru.micron.dto.omdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class FilmResponseDto(
    @JsonProperty("Title")
    var title: String? = null,
    @JsonProperty("Year")
    var year: String? = null,
    @JsonProperty("Rated")
    var ageRate: String? = null,
    @JsonProperty("Released")
    var releaseYear: String? = null,
    @JsonProperty("Runtime")
    var filmTime: String? = null,
    @JsonProperty("Genre")
    var genres: String? = null,
    @JsonProperty("Director")
    var director: String? = null,
    @JsonProperty("Writer")
    var writers: String? = null,
    @JsonProperty("Actors")
    var actors: String? = null,
    @JsonProperty("Plot")
    var plot: String? = null,
    @JsonProperty("Language")
    var language: String? = null,
    @JsonProperty("Country")
    var country: String? = null,
    @JsonProperty("Awards")
    var awards: String? = null,
    @JsonProperty("Poster")
    var posterUrl: String? = null,
    @JsonProperty("Ratings")
    var ratings: MutableList<RatingsDto>? = null,
    @JsonProperty("Metascore")
    var metascore: String? = null,
    var imdbRating: String? = null,
    var imdbVotes: String? = null,
    var imdbID: String? = null,
    @JsonProperty("Type")
    var type: String? = null,
    @JsonProperty("DVD")
    var dvd: String? = null,
    @JsonProperty("BoxOffice")
    var boxOffice: String? = null,
    @JsonProperty("Production")
    var production: String? = null,
    @JsonProperty("Website")
    var website: String? = null
)
