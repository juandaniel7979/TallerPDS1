package co.com.poli.tallerpds.service;

import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.service.dto.ProjectInDTO;

import java.util.List;

public interface ProjectService {

    Project create(ProjectInDTO project);

    List<Project> findAll();
}
