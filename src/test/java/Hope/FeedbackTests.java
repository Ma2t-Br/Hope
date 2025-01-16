package Hope;

import Hope.controller.feedback.FeedbackController;
import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.model.FeedbackRepository;
import Hope.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FeedbackTests {

    private FeedbackController feedbackController;
    private FeedbackService feedbackService;
    private HomeService homeService;
    private FeedbackRepository feedbackRepository;

    @BeforeEach
    void setUp() {
        feedbackRepository = Mockito.mock(FeedbackRepository.class);
        feedbackService = new FeedbackService(feedbackRepository);
        homeService = Mockito.mock(HomeService.class);
        feedbackController = new FeedbackController(feedbackService, homeService);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testAddCommentWithValidPrincipal() {
        int toolId = 1;
        String comment = "Great tool!";
        Principal principal = mock(Principal.class);
        User user = mock(User.class);

        when(principal.getName()).thenReturn("testUser");
        when(homeService.getUser("testUser")).thenReturn(user);

        String result = feedbackController.addComment(toolId, comment, principal);

        verify(homeService).getUser("testUser");
        verify(feedbackService).addComment(toolId, user, comment);
        assertEquals("redirect:/details/" + toolId, result);
    }

    @Test
    void testAddCommentWithoutPrincipal() {
        String result = feedbackController.addComment(1, "test comment", null);
        assertEquals("redirect:/login", result);
    }

    @Test
    void testAddCommentInService() {
        int toolId = 1;
        String comment = "Nice tool!";
        User user = mock(User.class);

        when(user.getId()).thenReturn(10);

        feedbackService.addComment(toolId, user, comment);

        verify(feedbackRepository).addComment(10, toolId, comment);
    }

    @Test
    void testGetComments() {
        int toolId = 1;
        when(feedbackRepository.findAllByToolId(toolId)).thenReturn(null);

        feedbackService.getComments(toolId);

        verify(feedbackRepository).findAllByToolId(toolId);
    }
}
