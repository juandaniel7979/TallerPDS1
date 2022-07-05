package co.com.poli.tallerpds.mapper.dto;

import co.com.poli.tallerpds.persistence.entity.Backlog;
import co.com.poli.tallerpds.persistence.entity.Project;
import co.com.poli.tallerpds.persistence.entity.ProjectTask;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInDTO {

    @NotBlank(message = "Este campo no puede estar en blanco")
    private String projectName;

    @NotBlank(message = "Este campo no puede estar en blanco")
    @Size(min = 5,max = 7)
    private String projectIdentifier;

    @NotBlank(message = "Este campo no puede estar en blanco")
    private String description;
    @JsonIgnore
    private Date startDate;
    @JsonIgnore
    private Date endDate;
    @JsonIgnore
    @JsonIgnoreProperties
    private Backlog backlog;

}
