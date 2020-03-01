package edu.java.SecurityRestDeveloperApi.security.jwt;

import edu.java.SecurityRestDeveloperApi.model.Role;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserDetailsFactory {

    private JwtUserDetailsFactory() {
    }

    public static JwtUserDetails createJwtUserDetails(User user) {
        JwtUserDetails jwtUserDetails = new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getPhones(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(user.getRoles())
                );
        return jwtUserDetails;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roleList) {
        return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
