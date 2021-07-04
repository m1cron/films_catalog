package ru.micron.libs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "Basic response model")
public class BasicResponse<T> {

  @Schema(description = "Payload data", nullable = true)
  private T data;

  @Schema(description = "Indicates whether the processing result is successful or not")
  @Default
  private boolean success = true;

  @Schema(description = "Appears only if success is false", nullable = true)
  private ErrorObject error;

  @Schema(description = "indicates whether this response is deprecated or not")
  private boolean deprecated;
}