package co.com.poli.tallerpds.service;

import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.service.dto.ProjectTaskInDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProjectTaskService {

//    ProjectTask create(ProjectTask projectTask);
    ProjectTask create(@RequestBody ProjectTaskInDTO projectTaskInDTO);

    List<ProjectTask> findByProjectIdentifier(String projectIdentifier);

    double findByProjectIdentifierHours(String projectIdentifier);

    double findByProjectIdentifierHoursDeleted(String projectIdentifier, String status);

    ProjectTask deleteIdAndProjectIdentifier(Long idTask, String projectIdentifier);

}
