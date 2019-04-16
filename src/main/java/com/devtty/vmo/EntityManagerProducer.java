package com.devtty.vmo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Produces
    @Default
    @RequestScoped
    public EntityManager create(){
        return this.emf.createEntityManager();
    }
    
    public void dispose(@Disposes @Default EntityManager em){
        if(em.isOpen()){
            em.close();
        }
    }
    
}
