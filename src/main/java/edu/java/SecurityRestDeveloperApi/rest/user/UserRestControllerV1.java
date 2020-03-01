package edu.java.SecurityRestDeveloperApi.rest.user;

import edu.java.SecurityRestDeveloperApi.dto.AuthenticationRequestDto;
import edu.java.SecurityRestDeveloperApi.dto.RegistrationRequestDto;
import edu.java.SecurityRestDeveloperApi.dto.VerifyDto;
import edu.java.SecurityRestDeveloperApi.model.*;
import edu.java.SecurityRestDeveloperApi.security.jwt.JwtTokenProvider;
import edu.java.SecurityRestDeveloperApi.service.PhoneService;
import edu.java.SecurityRestDeveloperApi.service.PhoneVerificationService;
import edu.java.SecurityRestDeveloperApi.service.RoleService;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import edu.java.SecurityRestDeveloperApi.util.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RoleService roleService;

    private final PhoneService phoneService;

    private final PhoneVerificationService phoneVerificationService;

    private final VerificationCodeGenerator verificationCodeGenerator;

    @Autowired
    public UserRestControllerV1(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserService userService,
            RoleService roleService,
            PhoneService phoneService,
            PhoneVerificationService phoneVerificationService,
            VerificationCodeGenerator verificationCodeGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.roleService = roleService;
        this.phoneService = phoneService;
        this.phoneVerificationService = phoneVerificationService;
        this.verificationCodeGenerator = verificationCodeGenerator;
    }

    @PostMapping(value = "login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        Map<Object, Object> response = new HashMap<>();
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(requestDto.getUsername());

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            response.put("username", username);
            response.put("token", token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "registration")
    public ResponseEntity registration(@RequestBody RegistrationRequestDto requestDto) {
        if (userService.findByUsername(requestDto.getUsername()) != null) {
            return ResponseEntity.badRequest().body("User with username: " + requestDto.getUsername() + " already exist");
        }
        User user = requestDto.toUser();
        List<Role> roles = new ArrayList<>();
        Role role = roleService.findByName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        Phone phone = requestDto.toPhone();
        phone.setVerificationCode(verificationCodeGenerator.generate());
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        user.setPhones(phones);
        phone.setUser(user);
        userService.save(user);
        phoneService.save(phone);
        phoneVerificationService.sendVerificationCode(phone);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("verificationCode", phone.getVerificationCode());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "verify")
    public ResponseEntity verify(@RequestBody VerifyDto verifyDto) {
        User user = userService.findByUsername(verifyDto.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body("User with username: " + verifyDto.getUsername() + " not found");
        }
        Phone phone = user.getPhones().get(0);
        if (phone.getVerificationCode() != Integer.parseInt(verifyDto.getVerificationCode())) {
            return ResponseEntity.badRequest().body("VerificationCode is incorrect!");
        }
        phone.setVerificationStatus(VerificationStatus.VERIFIED);
        user.setStatus(Status.ACTIVE);
        phoneService.update(phone);
        userService.update(user);
        return ResponseEntity.ok().body("Verification was successful");
    }
}
