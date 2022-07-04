package co.com.poli.tallerpds.mapper;

import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.persistence.entity.ProjectTaskStatus;
import co.com.poli.tallerpds.service.dto.ProjectTaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class ProjectTaskInDtoToTask implements IMapper<ProjectTaskInDTO, ProjectTask> {

    @Override
    public ProjectTask map(ProjectTaskInDTO in) {
        ProjectTask ProjectTask = new ProjectTask();
        ProjectTask.setName(in.getName());
        ProjectTask.setSummary(in.getSummary());
        ProjectTask.setAcceptanceCriteria(in.getAcceptanceCriteria());
        ProjectTask.setStatus(ProjectTaskStatus.NotStarted.toString());
        ProjectTask.setPriority(in.getPriority());
        ProjectTask.setHours(in.getHours());
        ProjectTask.setStartDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
//        ProjectTask.setEndDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        ProjectTask.setProjectIdentifier(in.getProjectIdentifier());
        ProjectTask.setBacklog(in.getBacklog());
        return ProjectTask;
    }
}
