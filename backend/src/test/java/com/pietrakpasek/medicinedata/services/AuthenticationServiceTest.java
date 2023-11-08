package com.pietrakpasek.medicinedata.services;

import com.pietrakpasek.medicinedata.exceptions.EmailExistsException;
import com.pietrakpasek.medicinedata.exceptions.NotMatchingPasswordException;
import com.pietrakpasek.medicinedata.model.DTO.UserLoginDTO;
import com.pietrakpasek.medicinedata.model.DTO.UserRegisterDTO;
import com.pietrakpasek.medicinedata.model.entities.Role;
import com.pietrakpasek.medicinedata.model.entities.User;
import com.pietrakpasek.medicinedata.repositories.RoleRepository;
import com.pietrakpasek.medicinedata.repositories.UserRepository;
import com.pietrakpasek.medicinedata.security.AuthenticationResponse;
import com.pietrakpasek.medicinedata.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("password", "password", "test@example.com", "ROLE_USER");

        Role role = new Role(1, "ROLE_USER");

        when(roleRepository.findRoleByName("ROLE_USER")).thenReturn(Optional.ofNullable(role));
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(), any())).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.register(userRegisterDTO);

        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
        assertEquals("ROLE_USER", role.getName());
        assertNotNull(response.getToken());
    }


    @Test
    public void testRegisterUser_DifferentPasswords() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("password", "differentpassword", "test@example.com", "ROLE_USER");

        assertThrows(NotMatchingPasswordException.class, () -> authenticationService.register(userRegisterDTO));
    }

    @Test
    public void testRegisterUser_ExistingEmail() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("password", "password", "test@example.com", "ROLE_USER");
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(new User()));

        assertThrows(EmailExistsException.class, () -> authenticationService.register(userRegisterDTO));
    }

    @Test
    public void testLogin_Success() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("test@example.com", "password");

        Role role = new Role(1, "ROLE_USER");

        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(new User("test@example.com", "encodedPassword", role)));
        when(jwtService.generateToken(any(), any())).thenReturn("jwtToken");

        AuthenticationResponse response = authenticationService.login(userLoginDTO);

        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
        assertEquals("ROLE_USER", role.getName());
        assertNotNull(response.getToken());
    }
    @Test
    public void testLogin_InvalidUser() {
        UserLoginDTO userLoginDTO = new UserLoginDTO("nonexistent@example.com", "wrongPassword");

        when(userRepository.findUserByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authenticationService.login(userLoginDTO));
    }

    @Test
    public void testIsAdmin_UserIsAdmin() {
        String jwt = "sampleJWTToken";

        Role role = new Role(1, "ROLE_ADMIN");

        when(jwtService.extractUsername(jwt)).thenReturn("test@example.com");
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(new User("test@example.com", "encodedPassword", role)));

        boolean isAdmin = authenticationService.isAdmin(jwt);

        assertTrue(isAdmin);
    }

    @Test
    public void testIsAdmin_UserIsNotAdmin() {
        String jwt = "sampleJWTToken";

        Role role = new Role(1, "ROLE_USER");

        when(jwtService.extractUsername(jwt)).thenReturn("test@example.com");
        when(userRepository.findUserByEmail("test@example.com")).thenReturn(Optional.of(new User("test@example.com", "encodedPassword", role)));

        boolean isAdmin = authenticationService.isAdmin(jwt);

        assertFalse(isAdmin);
    }

}
