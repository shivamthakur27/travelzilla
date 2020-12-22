package com.travelzilla.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class OAuthTokenUtil {

    @Autowired
    TokenStore tokenStore;

    public Map<String, Object> getUserDetails() {
        Map<String, Object> additionalInfo = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();

            if(details != null) {
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
                additionalInfo = accessToken.getAdditionalInformation();
            }
        }
        return additionalInfo;
    }
}
