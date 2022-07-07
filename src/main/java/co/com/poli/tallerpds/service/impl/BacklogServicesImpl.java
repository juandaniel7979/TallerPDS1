package co.com.poli.tallerpds.service.impl;

import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.repository.BacklogRepository;
import co.com.poli.tallerpds.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BacklogServicesImpl implements BacklogService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Override
    public Backlog create(Backlog backlog){
        return backlogRepository.save(backlog);
    }

    @Override
    public List<Backlog> findAll(){
        return backlogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Backlog findById(Long id) {
        return backlogRepository.findById(id).orElse(null) ;
    }

}
