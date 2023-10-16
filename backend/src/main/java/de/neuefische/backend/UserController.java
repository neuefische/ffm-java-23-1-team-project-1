package de.neuefische.backend;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public String getMe() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


// ...


}
