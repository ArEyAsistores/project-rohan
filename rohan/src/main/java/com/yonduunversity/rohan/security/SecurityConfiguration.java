package com.yonduunversity.rohan.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import static org.springframework.http.HttpMethod.*;
import com.yonduunversity.rohan.security.filter.CustomAuthenticationFilter;
import com.yonduunversity.rohan.security.filter.CustomAuthorizationFilter;

import static org.springframework.http.HttpMethod.GET;


@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationConfiguration authConfig)
            throws Exception {
        CustomAuthenticationFilter customAuthFilter = new CustomAuthenticationFilter(authConfig.getAuthenticationManager());
        customAuthFilter.setFilterProcessesUrl("/api/login");

        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeHttpRequests().requestMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers(GET,"/api/users/**").hasAuthority("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers(POST,"/api/user/add/sme").hasAuthority("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers(POST,"/api/course/**").hasAuthority("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers(GET,"/api/courses/**").hasAuthority("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers(PUT,"/api/user/**").hasAuthority("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers(POST,"/api/user/add/student").hasAuthority("SME");
        httpSecurity.authorizeHttpRequests().requestMatchers(GET,"/api/users/").hasAuthority("STUDENT");


        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.addFilter(customAuthFilter);
        httpSecurity.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}
