package com.travelzilla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${oauth.clientId}")
    private String clientId;

    @Value("${oauth.clientSecret}")
    private String clientSecret;

    @Value("${oauth.grantTypePassword}")
    private String grantTypePassword;

    @Value("${oauth.authorizationCode}")
    private String authorizationCode;

    @Value("${oauth.refreshToken}")
    private String refreshToken;

    @Value("${oauth.implicit}")
    private String implicit;

    @Value("${oauth.scopeRead}")
    private String scopeRead;

    @Value("${oauth.scopeWrite}")
    private String scopeWrite;

    @Value("${oauth.trust}")
    private String trust;

    @Value("${oauth.accessTokenValiditySeconds}")
    private int accessTokenValiditySeconds;

    @Value("${oauth.refreshTokenValiditySeconds}")
    private int refreshTokenValiditySeconds;

    @Value("${oauth.signingKey}")
    private String sigingKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory().withClient(clientId).secret(clientSecret)
                .authorizedGrantTypes(grantTypePassword, refreshToken)
                .scopes(scopeRead, scopeWrite, trust).accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .accessTokenConverter(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        converter.setSigningKey(sigingKey);
        return converter;
    }
}
