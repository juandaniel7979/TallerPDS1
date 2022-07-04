package co.com.poli.tallerpds.service.dto;

import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProjectInDTO {

    @NotBlank(message = "Este campo no puede estar en blanco")
    private String projectName;

    @NotBlank(message = "Este campo no puede estar en blanco")
    @Size(min = 5,max = 7)
    private String projectIdentifier;

    @NotBlank(message = "Este campo no puede estar en blanco")
    private String description;
    private Date startDate;
    private Date endDate;
    private Backlog backlog;

}
