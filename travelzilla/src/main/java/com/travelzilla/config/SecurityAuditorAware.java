package com.travelzilla.config;

import com.travelzilla.common.OAuthTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityAuditorAware {

    @Bean
    public AuditorAware<Integer> auditorAware(OAuthTokenUtil oAuthTokenUtil) {
        return () -> {
            long id=Long.parseLong(oAuthTokenUtil.getUserDetails().get("user_id").toString());
            return Optional.of((int)id);
        };
    }
}
