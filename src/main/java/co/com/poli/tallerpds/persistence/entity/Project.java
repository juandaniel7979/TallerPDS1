package co.com.poli.tallerpds.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;


    @NotBlank(message = "El campo no debe estar en blanco")
    @Column (name = "project_name",nullable = false)
    private String projectName;

    @NotNull
    @NotBlank(message = "El campo no debe estar e blanco")
    @Size(min = 5, max = 7, message = "El Campo debe tener minimo 5 caracteres maximo 7")
    @Column (name = "project_identifier",nullable = false,updatable = false,unique = true)
    private String projectIdentifier;


    @NotBlank(message = "El campo no debe estar e blanco")
    @Column (name = "description")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column (name = "start_date")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column (name = "end_date")
    private Date endDate;

//    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_backlog", referencedColumnName = "project_identifier")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonManagedReference
    @OneToOne(mappedBy = "project", cascade = CascadeType.PERSIST)
    @JoinColumn (name = "backlog")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Backlog backlog;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Project project = (Project) o;
//        return id.equals(project.id) && projectName.equals(project.projectName) && projectIdentifier.equals(project.projectIdentifier) && description.equals(project.description);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
