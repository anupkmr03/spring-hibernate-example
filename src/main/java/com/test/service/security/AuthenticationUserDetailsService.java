package com.test.service.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author anup on 2020-01-09 23:14
 * @project spring-hibernate-example
 */
@Service
public class AuthenticationUserDetailsService implements UserDetailsService
{
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if ("test".equals(username))
        {
            //https://www.browserling.com/tools/bcrypt
            return new User(
                "test",
                "$2a$10$UUX3dfT2jOoKZeQGcQzn4u16Wke/Q0HOCvRTqJGjImhVz9uholeTC",
                //freenow
                //$2a$10$SghJnMf9SvFwLdBLgfUVw.ms7uFic6YGVOuwxvya1w5eob459JDt2
                new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("user not found with name " + username);
        }
    }
}
