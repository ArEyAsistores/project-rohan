//package com.yonduuniversity.rohan.security.filter;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@Slf4j
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//        private final AuthenticationManager authManager;
//        private static final Date ACCESS_TOKEN_EXPIRE_AT = new Date(System.currentTimeMillis() + 10 * 60 * 1000); // 10
//                                                                                                                  // minutes
//        private static final Date REFRESH_TOKEN_EXPIRE_AT = new Date(System.currentTimeMillis() + 30 * 60 * 1000); // 30
//                                                                                                                   // minutes
//
//        public CustomAuthenticationFilter(AuthenticationManager authManager) {
//                this.authManager = authManager;
//        }
//
//        @Override
//        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//                        throws AuthenticationException {
//                String email = request.getParameter("email");
//                String password = request.getParameter("password");
//
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
//                                password);
//                return authManager.authenticate(authenticationToken);
//        }
//
//        @Override
//        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                        FilterChain chain,
//                        Authentication authResult) throws IOException, ServletException {
//                User user = (User) authResult.getPrincipal();
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//
//                String access_token = JWT.create()
//                                .withSubject(user.getUsername())
//                                .withExpiresAt(ACCESS_TOKEN_EXPIRE_AT)
//                                .withIssuer(request.getRequestURL().toString())
//                                .withClaim("roles",
//                                                user.getAuthorities()
//                                                                .stream()
//                                                                .map(GrantedAuthority::getAuthority)
//                                                                .collect(Collectors.toList()))
//                                .sign(algorithm);
//
//                String refresh_token = JWT.create()
//                                .withSubject(user.getUsername())
//                                .withExpiresAt(REFRESH_TOKEN_EXPIRE_AT)
//                                .withIssuer(request.getRequestURL().toString()).sign(algorithm);
//
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//        }
//}
