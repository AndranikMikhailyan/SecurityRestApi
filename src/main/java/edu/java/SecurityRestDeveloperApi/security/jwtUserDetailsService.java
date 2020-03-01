package edu.java.SecurityRestDeveloperApi.security;

import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.security.jwt.JwtUserDetails;
import edu.java.SecurityRestDeveloperApi.security.jwt.JwtUserDetailsFactory;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "jwtUserDetailsService")
@Slf4j
public class jwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public jwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUserDetails jwtUserDetails = JwtUserDetailsFactory.createJwtUserDetails(user);
        log.info("In loadUserByUsername user with username: {} successfully loaded", username);
        return jwtUserDetails;
    }
}
