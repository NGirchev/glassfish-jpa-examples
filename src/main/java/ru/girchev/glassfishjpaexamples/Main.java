package ru.girchev.glassfishjpaexamples;

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
public class Main {

    @Inject
    private Logger logger;

    @Inject
    @Named("mainService")
    private MainService mainService;

    @PostConstruct
    public void main() {
        logger.warning("POST CONSTRUCT!!!");
        mainService.main();
    }
}
