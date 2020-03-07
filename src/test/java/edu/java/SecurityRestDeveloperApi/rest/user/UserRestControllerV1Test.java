package edu.java.SecurityRestDeveloperApi.rest.user;

import edu.java.SecurityRestDeveloperApi.dto.AuthenticationRequestDto;
import edu.java.SecurityRestDeveloperApi.dto.RegistrationRequestDto;
import edu.java.SecurityRestDeveloperApi.dto.VerifyDto;
import edu.java.SecurityRestDeveloperApi.model.Phone;
import edu.java.SecurityRestDeveloperApi.model.Role;
import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.security.jwt.JwtTokenProvider;
import edu.java.SecurityRestDeveloperApi.service.PhoneService;
import edu.java.SecurityRestDeveloperApi.service.PhoneVerificationService;
import edu.java.SecurityRestDeveloperApi.service.RoleService;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import edu.java.SecurityRestDeveloperApi.util.VerificationCodeGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRestControllerV1Test {

    private AuthenticationManager authenticationManager = mock(AuthenticationManager.class);

    private JwtTokenProvider jwtTokenProvider = mock(JwtTokenProvider.class);

    private UserService userService = mock(UserService.class);

    private RoleService roleService = mock(RoleService.class);

    private PhoneService phoneService = mock(PhoneService.class);

    private PhoneVerificationService phoneVerificationService = mock(PhoneVerificationService.class);

    private VerificationCodeGenerator codeGenerator = mock(VerificationCodeGenerator.class);

    private UserRestControllerV1 userRestControllerV1 =
            new UserRestControllerV1(
                    authenticationManager,
                    jwtTokenProvider,
                    userService,
                    roleService,
                    phoneService,
                    phoneVerificationService,
                    codeGenerator);

    @Test
    void whenLoginAndPasswordIsValid() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();
        requestDto.setUsername("login");
        requestDto.setPassword("password");
        User user = new User();
        user.setUsername("login");
        user.setPassword("password");
        when(userService.findByUsername("login")).thenReturn(user);
        when(jwtTokenProvider.createToken("login", user.getRoles())).thenReturn("token.login");
        ResponseEntity response = userRestControllerV1.login(requestDto);
        Mockito.verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        assertEquals("{username=login, token=token.login}", response.getBody().toString());
        assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test
    void whenLoginOrPasswordIsInvalid() {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();
        requestDto.setUsername("login");
        requestDto.setPassword("password");
        User user = new User();
        user.setUsername("login");
        user.setPassword("password");
        when(userService.findByUsername("login")).thenReturn(null);
        assertThrows(BadCredentialsException.class, () -> userRestControllerV1.login(requestDto));
    }

    @Test
    void whenUsernameIsAvailable() {
        RegistrationRequestDto requestDto = new RegistrationRequestDto();
        requestDto.setUsername("login");
        when(userService.findByUsername("login")).thenReturn(null);
        when(roleService.findByName("ROLE_USER")).thenReturn(new Role());
        when(codeGenerator.generate()).thenReturn(111111);
        ResponseEntity registration = userRestControllerV1.registration(requestDto);
        assertEquals("{username=login, verificationCode=111111}", registration.getBody().toString());
    }

    @Test
    void whenUsernameIsNotAvailable() {
        RegistrationRequestDto requestDto = new RegistrationRequestDto();
        requestDto.setUsername("login");
        when(userService.findByUsername("login")).thenReturn(new User());
        ResponseEntity registration = userRestControllerV1.registration(requestDto);
        assertEquals("User with username: login already exist", registration.getBody().toString());
    }

    @Test
    void whenUserNotFound() {
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setUsername("login");
        verifyDto.setVerificationCode("111111");
        when(userService.findByUsername(verifyDto.getUsername())).thenReturn(null);
        ResponseEntity verify = userRestControllerV1.verify(verifyDto);
        assertEquals("User with username: login not found", verify.getBody().toString());
    }

    @Test
    void whenVerificationCodeIsIncorrect() {
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setUsername("login");
        verifyDto.setVerificationCode("111111");
        User user = new User();
        Phone phone = new Phone();
        phone.setVerificationCode(222222);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        user.setPhones(phones);
        when(userService.findByUsername(verifyDto.getUsername())).thenReturn(user);
        ResponseEntity verify = userRestControllerV1.verify(verifyDto);
        assertEquals("VerificationCode is incorrect!", verify.getBody().toString());
    }

    @Test
    void whenVerificationCodeIsCorrect() {
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setUsername("login");
        verifyDto.setVerificationCode("111111");
        User user = new User();
        Phone phone = new Phone();
        phone.setVerificationCode(111111);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        user.setPhones(phones);
        when(userService.findByUsername(verifyDto.getUsername())).thenReturn(user);
        ResponseEntity verify = userRestControllerV1.verify(verifyDto);
        assertEquals("Verification was successful", verify.getBody().toString());
    }
}