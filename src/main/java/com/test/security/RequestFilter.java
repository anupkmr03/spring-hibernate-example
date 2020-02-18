package com.test.security;

import com.test.service.security.AuthenticationUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter
{
    private final AuthenticationUserDetailsService authenticationUserDetailsService;
    private final TokenUtil tokenUtil;


    public RequestFilter(AuthenticationUserDetailsService authenticationUserDetailsService, TokenUtil tokenUtil)
    {
        this.authenticationUserDetailsService = authenticationUserDetailsService;
        this.tokenUtil = tokenUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException
    {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken = requestTokenHeader.substring(7);
            try
            {
                username = tokenUtil.getUsernameFromToken(jwtToken);
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e)
            {
                System.out.println("JWT Token has expired");
            }
        }
        else
        {
            logger.warn("JWT Token not found or null");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = this.authenticationUserDetailsService.loadUserByUsername(username);
            if (tokenUtil.validateToken(jwtToken, userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
