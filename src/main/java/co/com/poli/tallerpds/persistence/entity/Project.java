package co.com.poli.tallerpds.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "projects")

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long id;
    @Column (name = "project_name",nullable = false)
    private String projectName;
    @Column (name = "project_identifier",nullable = false,updatable = false,unique = true)
    private String projectIdentifier;
    @Column (name = "description",nullable = false)
    private String description;
    @Column (name = "start_date")
    private Date startDate;
    @Column (name = "end_date")
    private Date endDate;

    @OneToOne(mappedBy = "project", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "backlog_id", referencedColumnName = "id")
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
