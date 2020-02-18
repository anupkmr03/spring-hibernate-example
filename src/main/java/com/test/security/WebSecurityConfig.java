package com.test.security;

import com.test.service.security.AuthenticationUserDetailsService;
import com.test.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;
    private final AuthenticationUserDetailsService userDetailsService;
    private final RequestFilter requestFilter;


    public WebSecurityConfig(
        AuthenticationEntryPointImpl authenticationEntryPointImpl, AuthenticationUserDetailsService userDetailsService, RequestFilter requestFilter)
    {
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
        this.userDetailsService = userDetailsService;
        this.requestFilter = requestFilter;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers(
            Constants.AUTH_WHITELIST);
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf().disable()
            .authorizeRequests().antMatchers("/authenticate").permitAll().
            anyRequest().authenticated().and().
            // make sure we use stateless session; session won't be used to
            // store user's state.
                exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl).and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
