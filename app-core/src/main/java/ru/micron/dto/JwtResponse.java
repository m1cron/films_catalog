package ru.micron.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JwtResponse {

  private UUID uuid;
  private String token;
  private String type = "Bearer";
  private String username;
  private List<String> roles;
}
