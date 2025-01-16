package Hope.controller.signup;

import Hope.model.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SignUpControllerTest {

    private SignUpController signUpController;
    private SignUpService signUpService;

    @BeforeEach
    void setUp() {
        signUpService = Mockito.mock(SignUpService.class);
        signUpController = new SignUpController(signUpService);
    }

    @Test
    void testSignUpPage() {
        String result = signUpController.signUp();
        assertEquals("signUpPage", result);
    }

    @Test
    void testSignUpErrorPage() {
        Model model = mock(Model.class);
        String result = signUpController.signUpError(model);
        verify(model).addAttribute("signUpError", true);
        assertEquals("signUpPage", result);
    }

    @Test
    void testProcessSignUpSuccessful() {
        Model model = mock(Model.class);
        String username = "testUser";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";

        when(signUpService.signUp(username, password, firstName, lastName)).thenReturn(true);

        String result = signUpController.processSignUp(username, password, firstName, lastName, model);

        verify(signUpService).signUp(username, password, firstName, lastName);
        assertEquals("redirect:/login", result);
    }

    @Test
    void testProcessSignUpFailure() {
        Model model = mock(Model.class);
        String username = "testUser";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";

        when(signUpService.signUp(username, password, firstName, lastName)).thenReturn(false);

        String result = signUpController.processSignUp(username, password, firstName, lastName, model);

        verify(signUpService).signUp(username, password, firstName, lastName);
        verify(model).addAttribute("signUpError", true);
        assertEquals("signUpPage", result);
    }
}

public class SignUpServiceTest {

    private SignUpService signUpService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        signUpService = new SignUpService(userRepository);
    }

    @Test
    void testSignUpSuccessful() {
        String username = "testUser";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());
        doNothing().when(userRepository).insertUser(username, "encodedPassword", firstName, lastName);

        boolean result = signUpService.signUp(username, password, firstName, lastName);

        verify(userRepository, times(2)).findUserByUsername(username);
        verify(passwordEncoder).encode(password);
        verify(userRepository).insertUser(username, "encodedPassword", firstName, lastName);
        assertEquals(true, result);
    }

    @Test
    void testSignUpUserExists() {
        String username = "existingUser";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(Mockito.mock(User.class)));

        boolean result = signUpService.signUp(username, password, firstName, lastName);

        verify(userRepository).findUserByUsername(username);
        assertEquals(false, result);
    }

    @Test
    void testSignUpWithInvalidInput() {
        boolean result = signUpService.signUp("", "password", "John", "Doe");
        assertEquals(false, result);

        result = signUpService.signUp("username", "", "John", "Doe");
        assertEquals(false, result);

        result = signUpService.signUp("username", "password", "", "Doe");
        assertEquals(false, result);

        result = signUpService.signUp("username", "password", "John", "");
        assertEquals(false, result);
    }
}
