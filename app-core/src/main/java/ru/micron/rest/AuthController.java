package ru.micron.rest;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.micron.dto.BasicResponse;
import ru.micron.dto.JwtResponse;
import ru.micron.dto.UserDto;
import ru.micron.security.UserDetails;
import ru.micron.security.jwt.JwtUtils;
import ru.micron.service.UserService;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@Validated
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final UserService userService;

  @PostMapping("/login")
  public BasicResponse<JwtResponse> login(@RequestBody UserDto loginRequest) {
    return new BasicResponse<JwtResponse>()
        .setData(authenticate(loginRequest));
  }

  @PostMapping("/register")
  public BasicResponse<JwtResponse> registerUser(@RequestBody UserDto registerRequest) {
    if (userService.existsByUsername(registerRequest.getUsername())) {
      throw new AuthenticationServiceException(
          "User with username '" + registerRequest.getUsername() + "' already exists");
    }

    userService.register(registerRequest);

    log.info("Token was created for user with name {}", registerRequest.getUsername());
    return new BasicResponse<JwtResponse>()
        .setData(authenticate(registerRequest));
  }

  @PostMapping("/check")
  public BasicResponse<Void> checkIsRegistered(@RequestBody UserDto check) {
    return new BasicResponse<Void>()
        .setSuccess(userService.existsByUsername(check.getUsername()));
  }

  private JwtResponse authenticate(UserDto user) {
    Authentication authentication = authenticationManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword())
        );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    log.info("Token was created for user with name {}", user.getUsername());
    return new JwtResponse()
        .setUuid(userDetails.getUuid())
        .setToken(jwt)
        .setUsername(userDetails.getUsername())
        .setRoles(roles);
  }
}
