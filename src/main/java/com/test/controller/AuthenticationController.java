package com.test.controller;

import com.test.datatransferobject.RequestDTO;
import com.test.datatransferobject.ResponseDTO;
import com.test.security.TokenUtil;
import com.test.service.security.AuthenticationUserDetailsService;
import com.test.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController
{
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;
    private final AuthenticationUserDetailsService userDetailsService;


    public AuthenticationController(
        AuthenticationManager authenticationManager, TokenUtil tokenUtil, AuthenticationUserDetailsService userDetailsService)
    {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> createAuthenticationToken(@RequestBody RequestDTO authenticationRequest) throws Exception
    {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new ResponseDTO(token));
    }


    private void authenticate(String username, String password) throws Exception
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (DisabledException e)
        {
            throw new Exception(Constants.USER_DISABLED, e);
        }
        catch (BadCredentialsException e)
        {
            throw new Exception(Constants.INVALID_CREDENTIALS, e);
        }
    }
}
