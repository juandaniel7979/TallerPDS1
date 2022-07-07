package co.com.poli.tallerpds.service;

import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;

import java.util.List;

public interface BacklogService {

    Backlog create(Backlog backlog);

    List<Backlog> findAll();

    Backlog findById(Long id);
}
