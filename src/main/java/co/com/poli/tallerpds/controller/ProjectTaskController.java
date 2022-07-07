package co.com.poli.tallerpds.controller;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.helpers.ResponseBuild;
import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import co.com.poli.tallerpds.mapper.dto.ProjectTaskInDTO;
import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
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
    private final ResponseBuild builder;


//    @PostMapping
//    private Response create(@Valid @RequestBody ProjectTaskInDTO projectInDTO, BindingResult result){
//        if(result.hasErrors()){
//            return builder.failed(formatMessage(result));
//        }
//        projectTaskServices.create(projectInDTO);
//        return builder.success(projectInDTO);
//    }
    @PostMapping
    private Response create(@Valid @RequestBody ProjectTaskInDTO projectInDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        Backlog backlog = backlogServices.findById(projectInDTO.getBacklog().getId());
        if(backlog.getProjectIdentifier().equals(projectInDTO.getProjectIdentifier())){
            projectTaskServices.create(projectInDTO);
            return builder.success(projectInDTO);
        }
        return builder.failedNotFound("El projectIdentifier no coincide");
    }
    @GetMapping("/project/{projectIdentifier}")
    private Response findByProjectdentifier(@PathVariable("projectIdentifier") String projectIdentifier) {
        List<ProjectTask> projectTasks = projectTaskServices.findByProjectIdentifier(projectIdentifier);
        return builder.successFind(projectTasks);
    }

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
}
