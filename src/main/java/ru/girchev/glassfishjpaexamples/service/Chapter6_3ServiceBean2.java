package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Chapter6_3ServiceBean2 {

    @PersistenceContext(unitName="TestPU")
    EntityManager em;

    Project project;

    @Inject
    private Logger logger;

    /**
     * Without REQUIRES_NEW
     * javax.ejb.EJBException: There is an active transactional persistence
     * context for the same EntityManagerFactory
     * as the current stateful session bean's extended persistence context
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void contains() {
        logger.warning("6.3 contains FALSE=" + em.contains(project));
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
