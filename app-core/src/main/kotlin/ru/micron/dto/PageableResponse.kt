package ru.micron.dto

class PageableResponse<T>(
    var data: Collection<T>? = null,
    var pageable: PageableData? = null
)
