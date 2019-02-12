package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.ejb.*;
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
public class Chapter6_3ServiceBean {

    @PersistenceContext(unitName="TestPU", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    @EJB
    private Chapter6_3ServiceBean2 chapter6_3ServiceBean2;

    Project project;

    @Inject
    private Logger logger;

    public void initPrj() {
        project = em.find(Project.class, 1L);
        chapter6_3ServiceBean2.setProject(project);
        chapter6_3ServiceBean2.contains();
    }

    @Remove
    public void finished() {
    }
}
