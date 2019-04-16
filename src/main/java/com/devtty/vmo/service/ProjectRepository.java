package com.devtty.vmo.service;

import com.devtty.vmo.model.Project;
import java.util.UUID;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
@Repository(forEntity = Project.class)
public interface ProjectRepository extends EntityRepository<Project, UUID>{

}
