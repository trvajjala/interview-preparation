package com.tvajjala.rest.security.entry;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * AuthorizationFailHandler In case of Login Failure,it will throws the AccessDeniedException.
 */
public class AuthorizationFailHandler implements AccessDeniedHandler {

    /**
     * Handle.
     * 
     * @param request
     *            the request
     * @param response
     *            the response
     * @param accessDeniedException
     *            the access denied exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServletException
     *             the servletException
     * @see org.springframework.security.web.access.AccessDeniedHandler#handle
     */
    @Override
    public void handle( final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException accessDeniedException )
            throws IOException, ServletException {
        response.setContentType( "application/json" );
        try ( PrintWriter writer = response.getWriter() ) {
            writer.write( "{\"status\":\"403\",\"msg\":\"" + accessDeniedException.getMessage() + "\"}" );
        }
    }

}
