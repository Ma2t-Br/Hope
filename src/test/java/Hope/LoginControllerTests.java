package Hope.controller.login;

import Hope.model.User;
import Hope.model.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = Mockito.mock(LoginService.class);
        loginController = new LoginController(loginService);
    }

    @Test
    void testLoginPageWithError() {
        Model model = mock(Model.class);
        String result = loginController.login("true", null, model);

        verify(model).addAttribute("loginError", true);
        assertEquals("loginPage", result);
    }

    @Test
    void testLoginPageWithSessionExpired() {
        Model model = mock(Model.class);
        String result = loginController.login(null, "true", model);

        verify(model).addAttribute("sessionExpired", true);
        assertEquals("loginPage", result);
    }

    @Test
    void testProcessLoginSuccessful() {
        Model model = mock(Model.class);
        String username = "testUser";
        String password = "password";

        when(loginService.login(username, password)).thenReturn(true);

        String result = loginController.processLogin(username, password, model);

        verify(loginService).login(username, password);
        assertEquals("redirect:/home", result);
    }

    @Test
    void testProcessLoginFailure() {
        Model model = mock(Model.class);
        String username = "testUser";
        String password = "wrongPassword";

        when(loginService.login(username, password)).thenReturn(false);

        String result = loginController.processLogin(username, password, model);

        verify(loginService).login(username, password);
        verify(model).addAttribute("loginError", true);
        assertEquals("loginPage", result);
    }
}

public class LoginServiceTest {

    private LoginService loginService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        loginService = new LoginService(userRepository);
    }

    @Test
    void testLoginSuccessful() {
        String username = "testUser";
        String password = "password";
        User user = mock(User.class);

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn("encodedPassword");
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(true);

        boolean result = loginService.login(username, password);

        verify(userRepository).findUserByUsername(username);
        verify(passwordEncoder).matches(password, "encodedPassword");
        assertEquals(true, result);
    }

    @Test
    void testLoginInvalidUsername() {
        String username = "invalidUser";
        String password = "password";

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());

        boolean result = loginService.login(username, password);

        verify(userRepository).findUserByUsername(username);
        assertEquals(false, result);
    }

    @Test
    void testLoginInvalidPassword() {
        String username = "testUser";
        String password = "wrongPassword";
        User user = mock(User.class);

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn("encodedPassword");
        when(passwordEncoder.matches(password, "encodedPassword")).thenReturn(false);

        boolean result = loginService.login(username, password);

        verify(userRepository).findUserByUsername(username);
        verify(passwordEncoder).matches(password, "encodedPassword");
        assertEquals(false, result);
    }

    @Test
    void testLoginWithNullUsernameOrPassword() {
        boolean result = loginService.login(null, "password");
        assertEquals(false, result);

        result = loginService.login("username", null);
        assertEquals(false, result);
    }
}
