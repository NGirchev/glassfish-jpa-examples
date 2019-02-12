package ru.girchev.glassfishjpaexamples.service;

import ru.girchev.glassfishjpaexamples.domain.Department;
import ru.girchev.glassfishjpaexamples.domain.Employee;
import ru.girchev.glassfishjpaexamples.domain.Project;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.TransactionSynchronizationRegistry;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Chapter6_1ServiceBean {

    @PersistenceContext(unitName="TestPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Resource
    private TransactionSynchronizationRegistry txReg;

    Project project;
    @Inject
    private Logger logger;

    public void initPrj() {
        logger.warning("TX_STATUS2 = STATUS_ACTIVE | 0=" + txReg.getTransactionStatus());
        logger.warning("TX_KEY2 is txId=68");
        project = em.find(Project.class, 1L);
        logger.warning("6.1 contains TRUE=" + em.contains(project));
    }

    public void setNamePrj(String newName) {
        logger.warning("TX_STATUS3 = STATUS_ACTIVE | 0=" + txReg.getTransactionStatus());
        logger.warning("TX_KEY3 is txId=69");
        logger.warning("6.1 contains TRUE=" + em.contains(project));
        logger.warning("6.1 JOINED TRUE=" + em.isJoinedToTransaction());
        project.setName(newName);
    }

    public void print() {
        logger.warning("6.1 result=" +
                em.createNativeQuery("select p.name from chapter6.project p where p.id = 1")
                        .getSingleResult());
    }

    @Remove
    public void finished() {
    }
}
