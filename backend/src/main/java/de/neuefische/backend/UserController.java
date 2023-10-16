package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfile getMe() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken token) {

            return userService.createUserProfile(token);
        }
        return new UserProfile(auth.getName(), "", "");
    }

}
