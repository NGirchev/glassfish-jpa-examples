package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Chapter6_4ServiceBean {

    @PersistenceUnit(unitName="TestPU")
    EntityManagerFactory emf;
    EntityManager em;

    Project project;

    @Inject
    private Logger logger;

    public void initPrj() {
        em = emf.createEntityManager();
        project = em.find(Project.class, 1L);
        logger.warning("6.4 contains TRUE=" + em.contains(project));
    }

    public void setNamePrj(String newName) {
        logger.warning("6.4 JOINED1 TRUE=" + em.isJoinedToTransaction());
        logger.warning("6.4 contains TRUE=" + em.contains(project));
        em.joinTransaction();
        logger.warning("6.4 JOINED2 TRUE=" + em.isJoinedToTransaction());
        logger.warning("6.4 contains TRUE=" + em.contains(project));
        project.setName(newName);
    }

    public void print() {
        logger.warning("6.4 result=" +
                em.createNativeQuery("select p.name from chapter6.project p where p.id = 1")
                        .getSingleResult());
    }

    @Remove
    public void finished() {
    }
}
