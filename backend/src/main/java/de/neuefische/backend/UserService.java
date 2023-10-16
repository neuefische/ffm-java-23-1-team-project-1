package de.neuefische.backend;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {


    public UserProfile createUserProfile(OAuth2AuthenticationToken token) {

        Map<String, Object> attributes = token.getPrincipal().getAttributes();
        String id = attributes.get("id").toString();
        String userName = attributes.get("login").toString();
        String avatarUrl = attributes.get("avatar_url").toString();

        return new UserProfile(id, userName, avatarUrl);
    }


}
