package org.example.empmanage.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ElementCollection
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private Post post;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<Assignment> assignedProjects;

    public List<Assignment> getAssignedProjects() {
        return assignedProjects;
    }

    public Employee() {
    }

    public Employee(String name, String email, List<String> skills) {
        this.name = name;
        this.email = email;
        this.skills = skills;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills=" + skills +
                ", post=" + post +
                ", projects=" + (projects != null ? projects.size() : 0) +
                ", assignedProjects=" + (assignedProjects != null ? assignedProjects.size() : 0) +
                '}';
    }
}
