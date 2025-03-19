package org.example.empmanage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProjectDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProjectDAO() {
        emf = Persistence.createEntityManagerFactory("jpa2pro");
        em = emf.createEntityManager();
    }

    public List<Project> findAll() {
        List<Project> Projects = em.createQuery("Select p from Project p", Project.class).getResultList();
        System.out.println("Fetched projects: " + Projects);  // Log the result
        return Projects;
    }

}
