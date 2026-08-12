package ams.edu.vn.am_system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ams.edu.vn.am_system.Utils.CookieUtils;
import ams.edu.vn.am_system.dtos.LoginRequestDTO;
import ams.edu.vn.am_system.dtos.ResponseDTO;
import ams.edu.vn.am_system.dtos.UserDTO;
import ams.edu.vn.am_system.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
        UserDTO user = authService.login(request, response);
        return ResponseEntity.ok(new ResponseDTO(200, "Login successful !", user));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "access_token");
        CookieUtils.deleteCookie(request, response, "refresh_token");
        return ResponseEntity.ok(new ResponseDTO(200, "Logout successful!", null));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        authService.refresh_token(request, response);
        return ResponseEntity.ok(new ResponseDTO(200, "Token successfully refreshed!", null));
    }

}
