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
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectTaskInDTO {

    @NotBlank(message = "Este campo no puede estar en blanco")
    private String name;
    @NotBlank(message = "Este campo no puede estar en blanco")
    private String summary;
    @NotBlank(message = "Este campo no puede estar en blanco")
    private String projectIdentifier;
    @JsonIgnore
    private String acceptanceCriteria;
    @JsonIgnore
    private String status;
//    @Size(min = 1,max = 5)
    @Range(min = 1,max = 5, message = "El campo acepta un rango de 1 a 5")
    @Min(value = 0L, message = "El valor debe ser positivo")
    private int priority;
//    @Size(min = 1,max = 8)
    @Range(min = 1,max = 8, message = "El campo acepta un rango de 1 a 8")
    @Min(value = 0L, message = "El valor debe ser positivo")
    private double hours;
    private Date startDate;
    @JsonIgnore
    private Date endDate;
    private Backlog backlog;
}
