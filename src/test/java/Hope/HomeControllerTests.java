package Hope.controller.home;

import Hope.model.Tool;
import Hope.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    private HomeController homeController;
    private HomeService homeService;

    @BeforeEach
    void setUp() {
        homeService = Mockito.mock(HomeService.class);
        homeController = new HomeController(homeService);
    }

    @Test
    void testHomeWithPrincipal() {
        Principal principal = mock(Principal.class);
        Model model = mock(Model.class);
        User user = mock(User.class);
        List<Tool> tools = new ArrayList<>();

        when(principal.getName()).thenReturn("testUser");
        when(homeService.getUser("testUser")).thenReturn(user);
        when(homeService.getPreviewsData()).thenReturn(tools);

        String result = homeController.home(principal, model);

        verify(homeService).getUser("testUser");
        verify(model).addAttribute("username", null);
        verify(model).addAttribute("dataList", tools);
        assertEquals("home", result);
    }

    @Test
    void testHomeWithoutPrincipal() {
        Model model = mock(Model.class);

        String result = homeController.home(null, model);

        assertEquals("redirect:/login", result);
    }

    @Test
    void testSearchWithResults() {
        Principal principal = mock(Principal.class);
        Model model = mock(Model.class);
        User user = mock(User.class);
        List<Tool> tools = new ArrayList<>();

        when(principal.getName()).thenReturn("testUser");
        when(homeService.getUser("testUser")).thenReturn(user);
        when(homeService.searchData("query")).thenReturn(tools);

        String result = homeController.search("query", principal, model);

        verify(homeService).getUser("testUser");
        verify(model).addAttribute("username", null);
        verify(model).addAttribute("dataList", tools);
        verify(model).addAttribute("query", "query");
        assertEquals("home", result);
    }

    @Test
    void testSearchWithoutQuery() {
        Principal principal = mock(Principal.class);
        Model model = mock(Model.class);

        when(principal.getName()).thenReturn("testUser");

        String result = homeController.search("", principal, model);

        assertEquals("redirect:/home", result);
    }

    @Test
    void testSearchWithoutPrincipal() {
        Model model = mock(Model.class);

        String result = homeController.search("query", null, model);

        assertEquals("redirect:/login", result);
    }
}
