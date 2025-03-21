package org.example.empmanage.Controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.example.empmanage.DAO.EmployeeDAO;
import org.example.empmanage.Model.Assignment;
import org.example.empmanage.Model.Employee;
import org.example.empmanage.Model.Project;
import org.example.empmanage.DAO.ProjectDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {
    private Long selectedEmployeeId;
    private Long selectedProjectId;
    private int implication;
    private Employee employee = new Employee();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private ProjectDAO projectDAO = new ProjectDAO();
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    public EmployeeBean() {
        employees = employeeDAO.getAllEmployees();
        projects = projectDAO.findAll();
    }

    public void init() {
        employees = employeeDAO.getAllEmployees();
        projects = projectDAO.findAll();
    }

    public void addEmployee() {
        System.out.println("Saving Employee: " + employee);
        employeeDAO.addEmployee(employee);
        employees = employeeDAO.getAllEmployees();
        employee = new Employee();  // Reset form after adding
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployeesWithProjects();
    }


    public List<Project> getAllProjects() {
        return projectDAO.findAll();
    }

    public void deleteEmployee(Long employeeID) {
        System.out.println("Deleting employee: " + employeeID);
        employeeDAO.deleteEmployee(employeeID);
        employees = employeeDAO.getAllEmployees();
    }

    public void affectProject() {
        System.out.println("Assigning Employee: " + selectedEmployeeId + " to Project: " + selectedProjectId + " with Implication: " + implication + "%");
        employeeDAO.affectProject(selectedEmployeeId, selectedProjectId, implication);
        employees = employeeDAO.getAllEmployees();
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public Long getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(Long selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public int getImplication() {
        return implication;
    }

    public void setImplication(int implication) {
        this.implication = implication;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
