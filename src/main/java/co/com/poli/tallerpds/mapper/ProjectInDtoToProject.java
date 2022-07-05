package co.com.poli.tallerpds.mapper;

import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProjectInDtoToProject implements IMapper<ProjectInDTO, Project> {


    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public Project map(ProjectInDTO in){
        Project project = new Project();
        project.setProjectName(in.getProjectName());
        project.setDescription(in.getDescription());
        project.setStartDate(new Date(1999,11,10));
//        Project.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        project.setProjectIdentifier(in.getProjectIdentifier());
        project.setBacklog(in.getBacklog());
//        Project.setEndDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        return project;
    }
}
