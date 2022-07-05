package co.com.poli.tallerpds.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "backlogs")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_identifier",nullable = false)
    private String ProjectIdentifier;

//    @Column(name = projc
    @OneToOne(mappedBy = "backlog",cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JoinColumn(name = "id_project", referencedColumnName = "project_identifier")
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "backlog",fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<ProjectTask> projectTask;


}