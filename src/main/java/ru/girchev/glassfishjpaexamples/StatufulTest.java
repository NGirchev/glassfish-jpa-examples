package ru.girchev.glassfishjpaexamples;

import ru.girchev.glassfishjpaexamples.service.Chapter6_3ServiceBean;
import ru.girchev.glassfishjpaexamples.service.MainService;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 12.02.2019
 */
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
@DependsOn(value = "Main")
public class StatufulTest {

    @Inject
    private Logger logger;

    @EJB
    private Chapter6_3ServiceBean chapter6_3ServiceBean;

    @PostConstruct
    public void main() {
        logger.warning("StatufulTest begin!!!");
        chapter6_3ServiceBean.initPrj();
    }
}
