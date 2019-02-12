package ru.girchev.glassfishjpaexamples.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 12.02.2019
 */
@Named("mainService")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MainService {

    @EJB
    private Chapter6_0ServiceBean chapter60ServiceBean;
    @EJB
    private Chapter6_1ServiceBean chapter61ServiceBean;
    @EJB
    private Chapter6_2ServiceBean chapter62ServiceBean;

    @Inject
    private Logger logger;
    @Resource
    private TransactionSynchronizationRegistry txReg;

    // Caused by: javax.transaction.InvalidTransactionException:
    // Managed bean with Transactional annotation and TxType of NEVER
    // called inside a transaction context
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void main() {
        logger.warning("MAIN!!!");
        chapter60ServiceBean.fillDb();

        // @Stateless + PersistenceContextType.TRANSACTION
        // NOT changes in DB name
        // PC NOT shared between two method
        chapter60ServiceBean.initPrj();
        chapter60ServiceBean.setNamePrj("Project0");
        chapter60ServiceBean.print();

        //see javax.transaction.Status
        javax.transaction.Status status; //logger.warning("TX_KEY1=" + txReg.getTransactionKey());
        logger.warning("TX_STATUS1 STATUS_NO_TRANSACTION | 6=" + txReg.getTransactionStatus());

        // @Stateful + PersistenceContextType.TRANSACTION
        // changes in DB name
        // PC shared between two method, WAT?!
        chapter61ServiceBean.initPrj();
        chapter61ServiceBean.setNamePrj("Project1");
        chapter61ServiceBean.print();

        /*
         * Persistence Context Collision
         *
         * If we have transaction now with Active PC:
         * javax.ejb.EJBException: There is an active transactional persistence
         * context for the same EntityManagerFactory as the current stateful session
         * bean's extended persistence context
         *
         * Strange things...
         * 1. MainService(@Stateless without em) -> service1 (@Stateless em.TRANSACTION)
         * 2. MainService(@Stateless without em) -> service2 (@Stateful em.EXTENDED)
         *      -> Exception
         * But when
         * 1. MainService(Whithout JTA) -> service1 (@Stateless em.TRANSACTION)
         *      -> service2 (@Stateful em.EXTENDED) -> Has No Exception
         */

        // @Stateful + PersistenceContextType.EXTENDED
        // changes in DB name
        // PC shared between two method
        chapter62ServiceBean.initPrj();
        chapter62ServiceBean.setNamePrj("Project2");
        chapter62ServiceBean.print();

        // @Stateless + PersistenceContextType.EXTENDED
        // Caused by: java.lang.IllegalStateException: EntityManager with
        // PersistenceContextType.EXTENDED is not supported for this bean type
    }
}
