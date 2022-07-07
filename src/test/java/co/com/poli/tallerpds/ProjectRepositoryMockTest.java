package co.com.poli.tallerpds;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
public class ProjectRepositoryMockTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void when_findAll_return_ListProduct() {

        Project project = Project.builder()
                .projectName("Prueba Poli")
                .projectIdentifier("Algo1")
                .description("Proyecto de prueba")
                .build();
        projectRepository.save(project);
        List<Project> projectResponse = projectRepository.findAll();
        Assertions.assertThat(projectResponse.size()).isEqualTo(1 );
    }

}
