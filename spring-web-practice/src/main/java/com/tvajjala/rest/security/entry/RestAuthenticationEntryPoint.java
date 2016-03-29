package com.tvajjala.rest.security.entry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * The Class RestAuthenticationEntryPoint.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /** The logger. */
    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    /** The messageList. */
    static List<String> msgList = Arrays.asList("An Authentication object was not found in the SecurityContext",
            "Full authentication is required to access this resource");

    /**
     * @param request
     *            the request
     * @param response
     *            the response
     * @param exception
     *            the exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the ServletException
     */
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException,
            ServletException {

        logger.warn("authentication fails");

        final String msg = msgList.contains(exception.getMessage()) ? "invalid token entered or token expire " : exception.getMessage();
        response.setContentType("application/json");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"status\":\"200\",\"msg\":\"" + msg + "\"}");
        }

    }

}
