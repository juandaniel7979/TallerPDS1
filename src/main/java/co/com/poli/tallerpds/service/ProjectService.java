package co.com.poli.tallerpds.service;

import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import co.com.poli.tallerpds.persistence.entity.Project;

import java.util.List;

public interface ProjectService {

    Project create(ProjectInDTO project);
//    Project create(Project project);

    List<Project> findAll();
}
