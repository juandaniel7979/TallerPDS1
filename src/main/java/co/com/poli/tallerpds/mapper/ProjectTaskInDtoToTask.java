package co.com.poli.tallerpds.mapper;

import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.persistence.entity.ProjectTaskStatus;
import co.com.poli.tallerpds.mapper.dto.ProjectTaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class ProjectTaskInDtoToTask implements IMapper<ProjectTaskInDTO, ProjectTask> {

    @Override
    public ProjectTask map(ProjectTaskInDTO in) {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setName(in.getName());
        projectTask.setSummary(in.getSummary());
        projectTask.setAcceptanceCriteria(in.getAcceptanceCriteria());
        projectTask.setStatus(ProjectTaskStatus.NotStarted.toString());
        projectTask.setPriority(in.getPriority());
        projectTask.setHours(in.getHours());
        projectTask.setStartDate(new Date(1999-11-10));
//        projectTask.setEndDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        projectTask.setProjectIdentifier(in.getProjectIdentifier());
        projectTask.setBacklog(in.getBacklog());
        return projectTask;
    }
}
