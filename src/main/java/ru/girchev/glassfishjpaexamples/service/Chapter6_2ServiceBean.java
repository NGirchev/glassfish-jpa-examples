package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.ejb.Remove;
import javax.ejb.Stateful;
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
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Chapter6_2ServiceBean {

    @PersistenceContext(unitName="TestPU", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    Project project;

    @Inject
    private Logger logger;

    public void initPrj() {
        project = em.find(Project.class, 1L);
        logger.warning("6.2 contains TRUE=" + em.contains(project));
    }

    public void setNamePrj(String newName) {
        logger.warning("6.2 contains TRUE=" + em.contains(project));
        logger.warning("6.2 JOINED TRUE=" + em.isJoinedToTransaction());
        project.setName(newName);
    }

    public void print() {
        logger.warning("6.2 result=" +
                em.createNativeQuery("select p.name from chapter6.project p where p.id = 1")
                        .getSingleResult());
    }

    @Remove
    public void finished() {
    }
}
