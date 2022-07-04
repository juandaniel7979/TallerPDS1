package co.com.poli.tallerpds.mapper;

import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTaskStatus;
import co.com.poli.tallerpds.service.dto.ProjectInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class ProjectInDtoToProject implements IMapper<ProjectInDTO, Project> {

    @Override
    public Project map(ProjectInDTO in) {
        Project Project = new Project();
        Project.setProjectName(in.getProjectName());
        Project.setDescription(in.getDescription());
        Project.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        Project.setProjectIdentifier(in.getProjectIdentifier());
        Project.setBacklog(in.getBacklog());
//        Project.setEndDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        return Project;
    }
}
