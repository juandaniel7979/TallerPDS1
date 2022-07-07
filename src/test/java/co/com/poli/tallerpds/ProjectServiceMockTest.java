package co.com.poli.tallerpds;

import co.com.poli.tallerpds.mapper.ProjectInDtoToProject;
import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.repository.ProjectRepository;
import co.com.poli.tallerpds.service.ProjectService;
import co.com.poli.tallerpds.service.impl.ProjectServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProjectServiceMockTest {

    @Mock
    private ProjectRepository projectRepository;
    private ProjectService projectService;
    private ProjectInDtoToProject projectInDTO;


    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectServiceImpl(projectRepository,projectInDTO);

        Project project = Project.builder()
                .id(5L)
                .projectName("Prueba Poli")
                .projectIdentifier("Algo1")
                .description("Proyecto de prueba")
                .build();
        Mockito.when(projectRepository.findById(5L))
                .thenReturn(Optional.of(project));
    }

    @Test
    public void when_findById_return_Project() {
        Project project = projectService.findById(5L);
        Assertions.assertThat(project.getProjectName()).isEqualTo("Prueba Poli");
    }
}
