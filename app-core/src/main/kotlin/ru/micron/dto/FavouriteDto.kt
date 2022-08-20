package ru.micron.dto

import org.jetbrains.annotations.NotNull
import java.util.*

data class FavouriteDto(
    @NotNull var userId: UUID,
    @NotNull var imdbId: String
)
