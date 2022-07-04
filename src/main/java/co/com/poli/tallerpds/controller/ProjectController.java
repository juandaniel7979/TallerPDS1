package co.com.poli.tallerpds.controller;

import co.com.poli.tallerpds.helpers.Response;
import co.com.poli.tallerpds.helpers.ResponseBuild;
import co.com.poli.tallerpds.service.dto.ProjectInDTO;
import co.com.poli.tallerpds.service.impl.ProjectServiceImpl;
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
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServices;
    private final ResponseBuild builder;

    @PostMapping
    private Response create(@Valid @RequestBody ProjectInDTO projectInDTO, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        projectServices.create(projectInDTO);
        return builder.success(projectInDTO);
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
