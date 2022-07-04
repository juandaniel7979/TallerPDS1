package co.com.poli.tallerpds.service.dto;

import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectTaskInDTO {
    private String name;
    private String summary;
    private String projectIdentifier;
    private String acceptanceCriteria;
    private String status;
    private int priority;
    private double hours;
    private Date startDate;
    private Date endDate;
    private Backlog backlog;
}
