package com.devtty.vmo;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
public class FacesContextProducer {

    @Produces
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
}
