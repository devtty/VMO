package com.devtty.vmo.service;

import com.devtty.vmo.model.Modell;
import java.util.UUID;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
@Repository(forEntity = Modell.class)
public interface ModellRepository extends EntityRepository<Modell, UUID>{

}
