package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Employee;
import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Chapter6_0ServiceBean {

    @PersistenceContext(unitName="TestPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    Project project;

    @Inject
    private Logger logger;

    public void fillDb() {
        logger.warning("FILL DB!!!");
        project = new Project();
        project.setName("Project");
        em.persist(project);
    }

    public void initPrj() {
        project = em.find(Project.class, 1L);
        logger.warning("6.0 contains TRUE=" + em.contains(project));
    }

    public void setNamePrj(String newName) {
        logger.warning("6.0 contains FALSE=" + em.contains(project));
        logger.warning("6.0 JOINED TRUE=" + em.isJoinedToTransaction());
        project.setName(newName);
    }

    public void print() {
        logger.warning("6.0 result=" +
                em.createNativeQuery("select p.name from chapter6.project p where p.id = 1")
                        .getSingleResult());
    }
}
