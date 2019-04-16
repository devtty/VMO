package com.devtty.vmo;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.jboss.logging.Logger;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
public class LoggingProducer {

    @Produces
    public Logger getLogger(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass().getSimpleName());
    }
    
}
