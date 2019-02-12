package ru.girchev.glassfishjpaexamples.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * @author Girchev N.A.
 * Date: 12.02.2019
 */
public class LogProducer {

    @Produces
    public Logger producer(InjectionPoint ip){
        return Logger.getLogger(
                ip.getMember().getDeclaringClass().getName());
    }
}
