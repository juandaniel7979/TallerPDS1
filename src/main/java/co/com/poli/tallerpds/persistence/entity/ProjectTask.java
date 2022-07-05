package co.com.poli.tallerpds.persistence.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table (name = "project_tasks")

public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "summary")
    private String summary;
    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;
    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private int priority;
    @Column(name = "hours")
    private Double hours;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "project_identifier")
    private String projectIdentifier;

//    @Column(name = "backlog")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "backlog_id")
    @JsonIgnore
//    @JsonBackReference
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
