package com.rurallearning.platform.services;




import com.rurallearning.platform.dto.LoginRequest;
import com.rurallearning.platform.dto.LoginResponse;
import com.rurallearning.platform.entity.User;
import com.rurallearning.platform.repository.UserRepository;
import com.rurallearning.platform.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;

        private final BCryptPasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();

        public AuthService(UserRepository userRepository,
                           JwtService jwtService) {

            this.userRepository = userRepository;
            this.jwtService = jwtService;
        }

        public LoginResponse login(LoginRequest request) {

            // 1. Find user by email
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() ->
                            new RuntimeException("Invalid email or password"));

            // 2. Check password
            if (!passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword())) {

                throw new RuntimeException("Invalid email or password");
            }

            // 3. Generate JWT token
            String token = jwtService.generateToken(user.getEmail());

            // 4. Return login response
            return new LoginResponse(
                    token,
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );
        }

}
