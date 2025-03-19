package org.example.empmanage;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_project")
public class Assignment {
    @EmbeddedId
    private AssignmentId id;

    @ManyToOne
    @MapsId("employeeId") // Maps the embedded key field
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("projectId") // Maps the embedded key field
    @JoinColumn(name = "project_id")
    private Project project;

    private int implication;

    public Assignment() {}

    public Assignment(Employee employee, Project project, int implication) {
        this.id = new AssignmentId(employee.getId(), project.getId());
        this.employee = employee;
        this.project = project;
        this.implication = implication;
    }

    // Getters and Setters
    public AssignmentId getId() {
        return id;
    }

    public void setId(AssignmentId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getImplication() {
        return implication;
    }

    public void setImplication(int implication) {
        this.implication = implication;
    }
}
