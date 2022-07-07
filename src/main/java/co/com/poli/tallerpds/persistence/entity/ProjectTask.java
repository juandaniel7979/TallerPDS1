package co.com.poli.tallerpds.persistence.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table (name = "project_tasks")

public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Este campo no puede estar en blanco")
    @Column(name = "id")
    private Long id;
    @NotBlank(message = "Este campo no puede estar en blanco")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Este campo no puede estar en blanco")
    @Column(name = "summary")
    private String summary;
    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;
    @Column(name = "status")
    private String status;

    @Size(min = 1,max = 5)
    @Column(name = "priority")
    private int priority;
    @Range(min = 1,max = 8, message = "El campo acepta un rango de 1 a 8")
    @Min(value = 0L, message = "El valor debe ser positivo")
    @Column(name = "hours")
    private Double hours;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "project_identifier")
    private String projectIdentifier;

//    @Column(name = "backlog")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog")
    private Backlog backlog;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTask that = (ProjectTask) o;
        return id.equals(that.id) && name.equals(that.name) && summary.equals(that.summary) && projectIdentifier.equals(that.projectIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, summary);
    }
}
