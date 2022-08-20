package ru.micron.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Basic response model")
data class BasicResponse<T>(
    @Schema(description = "Payload data", nullable = true)
    var data: T? = null,
    @Schema(description = "Indicates whether the processing result is successful or not")
    var success: Boolean? = true,
    @Schema(description = "Appears only if success is false", nullable = true)
    var error: ErrorObject? = null,
    @Schema(description = "indicates whether this response is deprecated or not")
    var deprecated: Boolean? = false
) {
    constructor(data: T) : this(data, true)
    constructor(success: Boolean?) : this(null, success)
    constructor(data: T, success: Boolean?) : this(data, success, null)
}
