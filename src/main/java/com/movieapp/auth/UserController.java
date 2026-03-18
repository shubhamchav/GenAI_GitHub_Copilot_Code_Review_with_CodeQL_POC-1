package com.movieapp.auth;

import com.movieapp.auth.User;
import com.movieapp.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/me")
    public UserInfoResponse getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        // Fetch the user from the database to get user_type
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserInfoResponse(user.getUsername(), user.getUserType());
    }

    public static class UserInfoResponse {
        private String username;
        private String userType;

        public UserInfoResponse(String username, String userType) {
            this.username = username;
            this.userType = userType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
