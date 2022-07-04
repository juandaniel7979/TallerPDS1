package co.com.poli.tallerpds.controller;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.helpers.ResponseBuild;
import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import co.com.poli.tallerpds.service.dto.ProjectTaskInDTO;
import co.com.poli.tallerpds.service.impl.ProjectTaskImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project_task")
@RequiredArgsConstructor
public class ProjectTaskController {

    @Autowired
    private ProjectTaskImpl projectTaskServices;
    private final ResponseBuild builder;

    @PostMapping
    private Response create(@Valid @RequestBody ProjectTaskInDTO projectTaskInDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        projectTaskServices.create(projectTaskInDTO);
        return builder.success(projectTaskInDTO);
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
