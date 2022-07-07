package co.com.poli.tallerpds.controller;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.helpers.ResponseBuild;
import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.service.impl.BacklogServicesImpl;
import co.com.poli.tallerpds.service.impl.ProjectServiceImpl;
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
@RequestMapping("/backlog")
@RequiredArgsConstructor
public class BacklogController {

    @Autowired
    private BacklogServicesImpl backlogServices;
    private final ProjectServiceImpl projectService;
    private final ResponseBuild builder;

    @PostMapping
    private Response save(@Valid @RequestBody Backlog backlog, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
//        System.out.println(backlog.getProject().getId());
        Project projecti = new Project();
        try{
            projecti = projectService.findById(backlog.getProject().getId());
        }catch (NullPointerException e){
            return builder.failedNotFound("El projectIdentifier no coincide");
        }
        System.out.println(projecti.getProjectIdentifier());
        if(projecti.getProjectIdentifier().equals(backlog.getProjectIdentifier())){
//            System.out.println(projecti.getProjectIdentifier()+"$$$$$"+backlog.getProjectIdentifier());

            backlogServices.create(backlog);
            return builder.success(backlog);
        }
//        System.out.println(projecti.getProjectIdentifier()+"$$$$$"+backlog.getProjectIdentifier());
        return builder.failedNotFound("El projectIdentifier no coincide");
    }


    @GetMapping
    private Response findAll(){
        List<Backlog> backlogs = backlogServices.findAll();
        if(backlogs.isEmpty()){
            return builder.failedNotFound("Projects is empty");
        }
        return builder.successFind(backlogs);
    }

    private List<Map<String, String>> formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        return errors;
    }
}
