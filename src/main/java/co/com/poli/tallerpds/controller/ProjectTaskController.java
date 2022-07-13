package co.com.poli.tallerpds.controller;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.helpers.ResponseBuild;
import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import co.com.poli.tallerpds.mapper.dto.ProjectTaskInDTO;
import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.persistence.repository.ProjectTaskRepository;
import co.com.poli.tallerpds.service.impl.BacklogServicesImpl;
import co.com.poli.tallerpds.service.impl.ProjectTaskImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project_task")
@RequiredArgsConstructor
public class ProjectTaskController {

    private final ProjectTaskImpl projectTaskServices;
    private final BacklogServicesImpl backlogServices;

    private ProjectTaskRepository projectTaskRepository;
    private final ResponseBuild builder;


//    @PostMapping
//    private Response create(@Valid @RequestBody ProjectTaskInDTO projectInDTO, BindingResult result){
//        if(result.hasErrors()){
//            return builder.failed(formatMessage(result));
//        }
//        projectTaskServices.create(projectInDTO);
//        return builder.success(projectInDTO);
//    }

    @GetMapping
    public Response findAll() {
        List<ProjectTask> projects = projectTaskServices.findAll();
        if(projects.isEmpty()){
            return builder.failedNotFound("ProjectTask is empty");
        }
        return builder.successFind(projects);
    }

    @PostMapping
    public Response create(@Valid @RequestBody ProjectTaskInDTO projectInDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        Backlog backlog= new Backlog();
        try{
//            Backlog backlog = backlogServices.findById(projectInDTO.getBacklog().getId());
            backlog = backlogServices.findById(projectInDTO.getBacklog().getId());
            System.out.println("Llega al try");
        }catch (NullPointerException e){
            System.out.println("Llega al catch");
            return builder.failedNotFound("El id del backlog no existe");
        }
        System.out.println(backlog);
        if(backlog.getProjectIdentifier().equals(projectInDTO.getProjectIdentifier())){
            try{
            projectTaskServices.create(projectInDTO);
                return builder.success(projectInDTO);
            }catch(Exception e){
                System.out.println(e);
                return builder.failed(projectInDTO);
            }

        }
        return builder.failedNotFound("El projectIdentifier no coincide");
    }
    @GetMapping("/project/{projectIdentifier}")
    private Response findByProjectdentifier(@PathVariable("projectIdentifier") String projectId) {
        List<ProjectTask> projectTasks = projectTaskServices.findByProjectIdentifier(projectId);
        return builder.successFind(projectTasks);
    }

//    @GetMapping("/identifier/{projectIdentifier}")
//    public List<ProjectTask> findAllProjectIdentifier(@PathVariable("projectIdentifier") String project){
//        return projectTaskRepository.findByProjectIdentifier(project);
//    }

    @GetMapping("/hours/project/{projectIdentifier}")
    private Response findByProjectIdentifierHours(@PathVariable("projectIdentifier") String projectIdentifier){
        double projectTask = projectTaskServices.findByProjectIdentifierHours(projectIdentifier);
        return builder.successFind(projectTask);
    }
    @GetMapping("/hours/project{projectIdentifier}/{status}")
    private Response findByProjectIdentifierHoursDeleted(@PathVariable("projectIdentifier")String pojectIdentifier, @PathVariable("status") String status){
        double projectTask = projectTaskServices.findByProjectIdentifierHoursDeleted(pojectIdentifier, status);
        return builder.successFind(projectTask);
    }

    @DeleteMapping("/{idTask}/{projectIdentifier}")
    public Response deleteTask(@PathVariable("idTask") Long idTask, @PathVariable("projectIdentifier") String projectIdentifier){
        return builder.successFind(projectTaskServices.deleteIdAndProjectIdentifier(idTask, projectIdentifier));
    }

    private List<Map<String, String>> formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        return errors;
    }


    @PutMapping("/{idtask}/{projectIdentifier}")
    public Response changeStatusTask(@PathVariable("idtask") Long idTask, @PathVariable("projectIdentifier") String projectIdentifier) {
        boolean flag = projectTaskServices.changeStatusTask(idTask, projectIdentifier);
        if(flag) {
            return builder.success("Se logró actualizar el estado de la tarea");
        }else {
            return builder.failedNotFound("No se logró actualizar el estado, validar si la tarea existe");
        }
    }

}
