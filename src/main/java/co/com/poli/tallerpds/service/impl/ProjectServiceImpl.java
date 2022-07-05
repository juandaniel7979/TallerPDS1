package co.com.poli.tallerpds.service.impl;

import co.com.poli.tallerpds.mapper.ProjectInDtoToProject;
import co.com.poli.tallerpds.mapper.dto.ProjectInDTO;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.repository.ProjectRepository;
import co.com.poli.tallerpds.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    private ProjectInDtoToProject mapper;

    @Override
    public Project create(ProjectInDTO projectInDTO){

        try{
            Project project = mapper.map(projectInDTO);
            return projectRepository.save(project);
        }catch (Exception e){
            System.out.println(e);

        }
        return null;
    }
//
//    @Override
//    public Project create(Project project){
//        return projectRepository.save(project);
//    }

    @Override
    public List<Project> findAll(){
        return projectRepository.findAll();
    }

}
