package com.tvajjala.security.auth.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.tvajjala.persistence.vo.LoginRequest;
import com.tvajjala.persistence.vo.LoginResponse;
import com.tvajjala.persistence.vo.User;

public class JSONPayloadAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    HazelcastInstance hazelcastInstance;

    ObjectMapper jacksonObjectMapper;

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public void setJacksonObjectMapper(ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public JSONPayloadAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/login", "POST")); // if the URI and HTTP method matches the it is considers as login request
    }

    static boolean isContentTypeValid(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE);

    }

    LoginRequest getLoginRequest(final HttpServletRequest request) throws BadCredentialsException {

        try {
            final StringBuffer payload = new StringBuffer();
            String line = null;
            while ((line = request.getReader().readLine()) != null) {
                payload.append(line);
            }
            final LoginRequest loginRequest = jacksonObjectMapper.readValue(payload.toString(), LoginRequest.class);
            return loginRequest;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid attributes in the payload. Required username and password");
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException,
    ServletException {

        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            throw new InsufficientAuthenticationException("Invalid HTTP Method. it accepts only POST ");
        }

        if (!isContentTypeValid(request)) {
            throw new InsufficientAuthenticationException("Invalid content type. It accepts JSON only.");
        }

        final LoginRequest loginRequest = getLoginRequest(request);

        System.err.println("attemptAuthentication");
        final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        // Allow subclasses to set the "details" property
        // setDetails(request, authRequest);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.err.println("successfulAuthentication");
        final SecurityContextImpl sCtx = new SecurityContextImpl();
        sCtx.setAuthentication(authResult);

        // SecurityContextHolder.getContext().setAuthentication(authResult);
        final String sessionToken = sessionToken();

        hazelcastInstance.getMap("userTokenMap").put(sessionToken, sCtx);

        try (PrintWriter out = response.getWriter()) {

            final LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(sessionToken);

            if (authResult.getPrincipal() instanceof User) {
                final User user = (User) authResult.getPrincipal();
                loginResponse.setName(user.getUsername());
                loginResponse.setName(user.getUsername());
            }
            out.write(jacksonObjectMapper.writeValueAsString(loginResponse));
        }

        System.err.println("############  SUCCESSS ################");

    }

    static String sessionToken() {
        final String token = new BigInteger(130, new SecureRandom()).toString(32);
        return new String(Base64.encode(token.getBytes()));
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("requiresAuthentication  : " + request.getRequestURI());
        return "/api/login".equalsIgnoreCase(request.getRequestURI());
    }

}
