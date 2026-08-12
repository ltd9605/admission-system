package ams.edu.vn.am_system.services;

import org.springframework.security.core.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ams.edu.vn.am_system.Utils.CookieUtils;
import ams.edu.vn.am_system.Utils.JwtUtils;
import ams.edu.vn.am_system.dtos.LoginRequestDTO;
import ams.edu.vn.am_system.dtos.UserDTO;
import ams.edu.vn.am_system.entities.User;
import ams.edu.vn.am_system.exceptions.AppException;
import ams.edu.vn.am_system.repositories.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final UserRepo userRepo;

    public UserDTO login(LoginRequestDTO request, HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String accessToken = jwtUtils.generateAccessToken(userDetails);
            String refreshToken = jwtUtils.generateRefreshToken(userDetails);
            User userEntity = userRepo.findByUserName(userDetails.getUsername())
                    .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User not found in the database!"));
            if (!userEntity.getIsActive())
                throw new AppException(HttpStatus.FORBIDDEN, "Your username is not active!");
            CookieUtils.addCookie(response, "access_token", accessToken, 900000);
            CookieUtils.addCookie(response, "refresh_token", refreshToken, 604800000);
            UserDTO user = new UserDTO(userEntity);
            return user;
        } catch (AuthenticationException e) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Incorrect account or password!");
        }
    }

    public void refresh_token(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookieValue(request, "refresh_token");
        if (refreshToken == null || refreshToken.isEmpty())
            throw new AppException(HttpStatus.UNAUTHORIZED, "Refresh token not found, please login again!");
        if (jwtUtils.validateJwtToken(refreshToken)) {
            String username = jwtUtils.getUserNameFromJwtToken(refreshToken);
            User user = userRepo.findByUserName(username)
                    .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User not found in the database!"));
            if (!user.getIsActive())
                throw new AppException(HttpStatus.FORBIDDEN, "Your username is not active!");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String newtoken = jwtUtils.generateAccessToken(userDetails);
            CookieUtils.addCookie(response, "access_token", newtoken, 900000);
        } else {
            throw new AppException(HttpStatus.FORBIDDEN, "The refresh token is invalid or has expired!");
        }
    }
}
