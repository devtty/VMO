
package com.devtty.vmo.view;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.View;
import static org.apache.deltaspike.jsf.api.config.view.View.NavigationMode.REDIRECT;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
public interface Pages {
    
    @View(basePath = "/")
    class Home implements ViewConfig {}
    
    @View(basePath = "/", navigation = REDIRECT)
    class NewProject implements ViewConfig {}
    
}
