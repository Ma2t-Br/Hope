package Hope.controller.tool;

import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.model.Tool;
import Hope.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToolControllerTest {

    private ToolController toolController;
    private ToolService toolService;
    private FeedbackService feedbackService;
    private HomeService homeService;

    @BeforeEach
    void setUp() {
        toolService = Mockito.mock(ToolService.class);
        feedbackService = Mockito.mock(FeedbackService.class);
        homeService = Mockito.mock(HomeService.class);
        toolController = new ToolController(toolService, feedbackService, homeService);
    }

    @Test
    void testIndex() {
        String result = toolController.index();
        assertEquals("redirect:/login", result);
    }

    @Test
    void testShowAllMainData() {
        Model model = mock(Model.class);
        List<Tool> tools = new ArrayList<>();

        when(toolService.getAllMainTool()).thenReturn(tools);

        String result = toolController.showAllMainData(model);

        verify(toolService).getAllMainTool();
        verify(model).addAttribute("dataList", tools);
        assertEquals("home", result);
    }

    @Test
    void testGetDataByIdWithValidUser() {
        int toolId = 1;
        Model model = mock(Model.class);
        Principal principal = mock(Principal.class);
        Tool tool = mock(Tool.class);
        User user = mock(User.class);
        List<String> comments = new ArrayList<>();

        when(principal.getName()).thenReturn("testUser");
        when(homeService.getUser("testUser")).thenReturn(user);
        when(toolService.getTool(toolId)).thenReturn(Optional.of(tool));
        when(feedbackService.getComments(toolId)).thenReturn(comments);

        String result = toolController.getDataById(toolId, model, principal);

        verify(homeService).getUser("testUser");
        verify(toolService).getTool(toolId);
        verify(feedbackService).getComments(toolId);
        verify(model).addAttribute("data", tool);
        verify(model).addAttribute("comments", comments);
        assertEquals("details", result);
    }

    @Test
    void testGetDataByIdWithoutUser() {
        int toolId = 1;
        Model model = mock(Model.class);

        String result = toolController.getDataById(toolId, model, null);

        assertEquals("redirect:/login", result);
    }

    @Test
    void testDeleteDataById() {
        int toolId = 1;

        String result = toolController.deleteDataById(toolId);

        verify(toolService).deleteTool(toolId);
        assertEquals("redirect:/mainData", result);
    }
}

public class ToolServiceTest {

    private ToolService toolService;
    private ToolRepository toolRepository;

    @BeforeEach
    void setUp() {
        toolRepository = Mockito.mock(ToolRepository.class);
        toolService = new ToolService(toolRepository);
    }

    @Test
    void testGetAllMainTool() {
        List<Tool> tools = new ArrayList<>();

        when(toolRepository.findAll()).thenReturn(tools);

        List<Tool> result = toolService.getAllMainTool();

        verify(toolRepository).findAll();
        assertEquals(tools, result);
    }

    @Test
    void testGetToolById() {
        int toolId = 1;
        Tool tool = mock(Tool.class);

        when(toolRepository.findById(toolId)).thenReturn(Optional.of(tool));

        Optional<Tool> result = toolService.getTool(toolId);

        verify(toolRepository).findById(toolId);
        assertEquals(Optional.of(tool), result);
    }

    @Test
    void testDeleteTool() {
        int toolId = 1;

        toolService.deleteTool(toolId);

        verify(toolRepository).deleteById(toolId);
    }

    @Test
    void testUpdateTool() {
        Tool tool = mock(Tool.class);

        toolService.updateTool(tool);

        verify(toolRepository).save(tool);
    }
}
