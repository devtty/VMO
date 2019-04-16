package com.devtty.vmo.view;

import com.devtty.vmo.model.Project;
import com.devtty.vmo.service.ProjectRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.WindowScoped;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
@Named
@WindowScoped
public class ProjectIndexView implements Serializable{
    
    @Inject ProjectRepository projectRepository;
    
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    @PostConstruct
    public void init(){
        setProjects(projectRepository.findAll());
    }

}
