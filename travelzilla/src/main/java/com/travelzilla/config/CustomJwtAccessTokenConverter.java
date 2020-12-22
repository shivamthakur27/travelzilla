package com.travelzilla.config;

import com.travelzilla.dto.CustomUserDetailsDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    private static final String EMAIL = "email";
    private static final String ROLE_ID = "role_id";
    private static final String USER_ID = "user_id";
    private static final String NAME = "name";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUserDetailsDto user = (CustomUserDetailsDto) authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put(EMAIL, user.getEmail());
        additionalInfo.put(ROLE_ID, user.getRoleId());
        additionalInfo.put(USER_ID, user.getId());
        additionalInfo.put(NAME, user.getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        accessToken = super.enhance(accessToken, authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return accessToken;
    }
}