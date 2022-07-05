package co.com.poli.tallerpds.service.impl;

import co.com.poli.tallerpds.mapper.ProjectTaskInDtoToTask;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.persistence.entity.ProjectTaskStatus;
import co.com.poli.tallerpds.persistence.repository.ProjectRepository;
import co.com.poli.tallerpds.persistence.repository.ProjectTaskRepository;
import co.com.poli.tallerpds.service.ProjectTaskService;
import co.com.poli.tallerpds.mapper.dto.ProjectTaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskImpl implements ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    private ProjectTaskInDtoToTask mapper;

//    @Override
//    public ProjectTask create(ProjectTask projectTask){
//
//        return projectTaskRepository.save(projectTask);
//    }
//
    @Override
    public ProjectTask create(ProjectTaskInDTO projectTaskInDTO){
        ProjectTask projectTask = mapper.map(projectTaskInDTO);
        return projectTaskRepository.save(projectTask);
    }
    @Override
    public List<ProjectTask> findByProjectIdentifier(String projectIdentifier){
        List<Project> projects = projectRepository.findAll();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectIdentifier().equals(projectIdentifier)) {
                return projects.get(i).getBacklog().getProjectTask();
            }
        }

        return null;
    }
    @Override
    public double findByProjectIdentifierHours(String projectIdentifier){
        List<ProjectTask> taskList = findByProjectIdentifier(projectIdentifier);
        double hours = 0;

        for(int i = 0; i < taskList.size(); i++) {
            if(!taskList.get(i).getStatus().equals("deleted")){
                hours += taskList.get(i).getHours();
            }
        }
        return hours;
    }

    @Override
    public double findByProjectIdentifierHoursDeleted(String projectIdentifier, String status){
        List<ProjectTask> projectTaskList = findByProjectIdentifier(projectIdentifier);

        double hours = 0;

        for (int i = 0; i < projectTaskList.size(); i++){
            if (projectTaskList.get(i).getStatus().equals(status)){
                hours += projectTaskList.get(i).getHours();
            }
        }

        return hours;
    }
    @Override
    public ProjectTask deleteIdAndProjectIdentifier(Long idTask, String projectIdentifier){
        List<ProjectTask> projectTaskList = findByProjectIdentifier(projectIdentifier);

        for (int i = 0; i < projectTaskList.size(); i++){
            if(projectTaskList.get(i).getId() == idTask){
                projectTaskList.get(i).setStatus(ProjectTaskStatus.Deleted.toString());
                projectTaskRepository.delete(projectTaskList.get(i));
                return projectTaskList.get(i);
            }
        }
        return null;
    }
}
