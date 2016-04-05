package com.tvajjala.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.tvajjala.persistence.vo.LoginRequest;
import com.tvajjala.persistence.vo.LoginResponse;
import com.tvajjala.persistence.vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        out.write("Welcome  to Spring boot rest template");
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (isContentTypeValid(request)) {
            try (PrintWriter out = response.getWriter()) {
                out.write(getMapper(request).writeValueAsString("Invalid content type. It accepts only JSON"));
            }
            return;
        }

        try {
            final LoginRequest loginRequest = getLoginRequest(request);

            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword());

            final ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());

            final AuthenticationManager authenticationManager = (AuthenticationManager) ctx.getBean("authenticationManager");

            final HazelcastInstance hazelcastInstance = (HazelcastInstance) ctx.getBean("hazelcastInstance");

            final Authentication authentication = authenticationManager.authenticate(authenticationToken);

            final SecurityContextImpl sCtx = new SecurityContextImpl();
            sCtx.setAuthentication(authentication);

            final String sessionToken = sessionToken();

            hazelcastInstance.getMap("userTokenMap").put(sessionToken, sCtx);

            try (PrintWriter out = response.getWriter()) {

                final LoginResponse loginResponse = new LoginResponse();
                loginResponse.setAccessToken(sessionToken);

                if (authentication.getPrincipal() instanceof User) {
                    final User user = (User) authentication.getPrincipal();
                    loginResponse.setName(user.getUsername());
                    loginResponse.setName(user.getUsername());
                }
                out.write(getMapper(request).writeValueAsString(loginResponse));
            }

        } catch (final Exception e) {
            try (PrintWriter writer = response.getWriter()) {
                writer.write(getMapper(request).writeValueAsString(e.getLocalizedMessage()));
            }
        }

    }

    static LoginRequest getLoginRequest(final HttpServletRequest request) throws BadCredentialsException {

        try {
            final StringBuffer payload = new StringBuffer();
            String line = null;
            while ((line = request.getReader().readLine()) != null) {
                payload.append(line);
            }
            final LoginRequest loginRequest = getMapper(request).readValue(payload.toString(), LoginRequest.class);
            return loginRequest;
        } catch (final Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid attributes in the payload. Required username and password");
        }
    }

    static ObjectMapper getMapper(final HttpServletRequest request) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean("jacksonObjectMapper", ObjectMapper.class);
    }

    public static String sessionToken() {
        final String token = new BigInteger(130, new SecureRandom()).toString(32);
        return new String(Base64.encode(token.getBytes()));
    }

    static boolean isContentTypeValid(final HttpServletRequest request) {
        return request.getContentType() == null || !request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE);

    }

}
