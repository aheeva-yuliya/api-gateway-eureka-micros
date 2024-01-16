package com.micros.order.interceptor;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Interceptor {
    private final BeanFactory beanFactory;

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        log.info("lets apply token");
        return requestTemplate -> {
            JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext()
                    .getAuthentication();
            log.info("order interceptor applied token " + token.getToken().getTokenValue());
            requestTemplate.header("Authorization", "Bearer " + token.getToken().getTokenValue());
        };
    }
}
