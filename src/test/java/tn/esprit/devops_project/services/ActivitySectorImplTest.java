package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.devops_project.controllers.ActivitySectorController;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.services.Iservices.IActivitySector;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ActivitySectorImplTest {
    private MockMvc mockMvc;

    @Mock
    private IActivitySector activitySectorService;

    @InjectMocks
    private ActivitySectorController activitySectorController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(activitySectorController).build();
    }

    @Test
    public void testRetrieveAllActivitySectors() throws Exception {
        ActivitySector sector1 = new ActivitySector(1L, "Code1", "Sector 1", null);
        ActivitySector sector2 = new ActivitySector(2L, "Code2", "Sector 2", null);

        List<ActivitySector> sectors = Arrays.asList(sector1, sector2);

        when(activitySectorService.retrieveAllActivitySectors()).thenReturn(sectors);

        mockMvc.perform(get("/activitySector")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idSecteurActivite", is(1)))
                .andExpect(jsonPath("$[1].idSecteurActivite", is(2)));

        verify(activitySectorService, times(1)).retrieveAllActivitySectors();
        verifyNoMoreInteractions(activitySectorService);
    }

    // Add similar test methods for other controller endpoints (add, delete, update, retrieve by id).
}