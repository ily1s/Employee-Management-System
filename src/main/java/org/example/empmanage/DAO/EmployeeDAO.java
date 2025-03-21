package org.example.empmanage.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.empmanage.Model.Assignment;
import org.example.empmanage.Model.Employee;
import org.example.empmanage.Model.Project;

import java.util.List;

public class EmployeeDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public EmployeeDAO() {
        emf = Persistence.createEntityManagerFactory("jpa2pro");
        em = emf.createEntityManager();
    }

    public void addEmployee(Employee employee) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employees = em.createQuery("Select e from Employee e", Employee.class).getResultList();
        System.out.println("Fetched employers: " + employees);  // Log the result
        return employees;
    }

    public void deleteEmployee(Long EmployeeId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee Employer = em.find(Employee.class, EmployeeId);
            if (Employer != null) {
                em.remove(Employer);
            }
            transaction.commit();
            System.out.println("Employee deleted successfully: " + EmployeeId);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public void affectProject(Long employeeId, Long projectId, int implication) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee employee = em.find(Employee.class, employeeId);
            Project project = em.find(Project.class, projectId);

            if (employee == null || project == null) {
                System.out.println("Employee or Project not found!");
                return;
            }

            Assignment assignment = new Assignment();
            assignment.setEmployee(employee);
            assignment.setProject(project);
            assignment.setImplication(implication);

            em.persist(assignment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployeesWithProjects() {
        return em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH e.assignedProjects", Employee.class)
                .getResultList();
    }

    public Employee findById(Long selectedEmployeeId) {
        return em.find(Employee.class, selectedEmployeeId);
    }
}
