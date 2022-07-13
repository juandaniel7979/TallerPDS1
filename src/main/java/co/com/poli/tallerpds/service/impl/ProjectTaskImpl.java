package co.com.poli.tallerpds.service.impl;

import co.com.poli.tallerpds.mapper.ProjectTaskInDtoToTask;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.persistence.entity.ProjectTaskStatus;
import co.com.poli.tallerpds.persistence.repository.ProjectRepository;
import co.com.poli.tallerpds.persistence.repository.ProjectTaskRepository;
import co.com.poli.tallerpds.service.ProjectTaskService;
import co.com.poli.tallerpds.mapper.dto.ProjectTaskInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin
@Service
public class ProjectTaskImpl implements ProjectTaskService {

    @Autowired
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectTaskInDtoToTask mapper;

//    @Override
//    public ProjectTask create(ProjectTask projectTask){
//
//        return projectTaskRepository.save(projectTask);
//    }
//
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectTask create(ProjectTaskInDTO projectTaskInDTO){

        try{
        ProjectTask projectTask = mapper.map(projectTaskInDTO);
            System.out.println("Se transformó el task");
            if(ValidarStatus(projectTask.getStatus())){
                return projectTaskRepository.save(projectTask);
            }

        }catch (Exception e){
            System.out.println(e);
//            return null;
        }
        System.out.println("Se salió y retorna null");
        return null;
    }
    @Override
    public List<ProjectTask> findByProjectIdentifier(String projectIdentifier){
        List<ProjectTask> projects = projectTaskRepository.findAll();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectIdentifier().equals(projectIdentifier)) {
                return projects.get(i).getBacklog().getProjectTask();
            }
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectTask> viewAllTaskProject(String projectIdentifier) {
        return projectTaskRepository.findByProjectIdentifier(projectIdentifier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectTask> findAll() {
        return projectTaskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public double findByProjectIdentifierHours (String projectIdentifier){
        List<ProjectTask> taskList = projectTaskRepository.findByProjectIdentifier(projectIdentifier);
        double hours = 0;

        for(int i = 0; i < taskList.size(); i++) {
            if(!taskList.get(i).getStatus().equals("deleted")){
                hours += taskList.get(i).getHours();
            }
        }
        return hours;
    }

    @Override
    @Transactional(readOnly = true)
    public double findByProjectIdentifierHoursDeleted(String projectIdentifier, String status){
        List<ProjectTask> projectTaskList = projectTaskRepository.findByProjectIdentifier(projectIdentifier);

        double hours = 0;

        for (int i = 0; i < projectTaskList.size(); i++){
            if (projectTaskList.get(i).getStatus().equals(status)){
                hours += projectTaskList.get(i).getHours();
            }
        }

        return hours;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectTask deleteIdAndProjectIdentifier(Long idTask, String projectIdentifier){
        List<ProjectTask> projectTaskList = projectTaskRepository.findByProjectIdentifier(projectIdentifier);

        for (int i = 0; i < projectTaskList.size(); i++){
            if(projectTaskList.get(i).getId() == idTask){
                projectTaskList.get(i).setStatus(ProjectTaskStatus.Deleted.toString());
                projectTaskRepository.delete(projectTaskList.get(i));
                return projectTaskList.get(i);
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatusTask(Long idTask,String projectIdentifier) {
        Optional<ProjectTask> borradoLogico = projectTaskRepository.findById(idTask);

        if(borradoLogico.isPresent() && borradoLogico.get().getProjectIdentifier().equals(projectIdentifier)) {
            borradoLogico.get().setStatus("deleted");
            return true;
        }else {
            return false;
        }
    }

    private boolean ValidarStatus(String nameStatus) {
        if(nameStatus.equals("Not started") || nameStatus.equals("In progress") || nameStatus.equals("completed") || nameStatus.equals("deleted")) {
            return true;
        }else {
            return false;
        }
    }
}
