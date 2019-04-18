package com.devtty.vmo.view;

import com.devtty.vmo.model.Modell;
import com.devtty.vmo.model.Project;
import com.devtty.vmo.service.ModellRepository;
import com.devtty.vmo.service.ProjectRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Denis Renning <denis@devtty.de>
 */
@Named
@ViewAccessScoped
public class ProjectDetailView implements Serializable{
    
    @Inject Logger logger;
    @Inject ViewNavigationHandler viewNavigationHandler;
    
    @Inject ModellRepository modellRepository;
    @Inject ProjectRepository projectRepository;
    
    private List<Modell> selectableModells;
    private Modell selectedModell;
    
    private Project project = new Project();
    private UploadedFile upload;
    

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public UploadedFile getUpload() {
        return upload;
    }

    public void setUpload(UploadedFile upload) {
        this.upload = upload;
    }

    public List<Modell> getSelectableModells() {
        return selectableModells;
    }

    public void setSelectableModells(List<Modell> selectableModells) {
        this.selectableModells = selectableModells;
    }

    public Modell getSelectedModell() {
        return selectedModell;
    }

    public void setSelectedModell(Modell selectedModell) {
        this.selectedModell = selectedModell;
    }
    
    
    
    public void update(){
        logger.info(this.project.getName());
        if(upload!=null){
            logger.info("CF: " + upload.getFileName());
            logger.info("CC: " + upload.getContentType());
            
            if(upload.getContentType().equals("text/xml")){
                XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
                try{
                    XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(upload.getInputstream());
                    while(xmlEventReader.hasNext()){
                        XMLEvent xmlEvent = xmlEventReader.nextEvent();
                        
                        if(xmlEvent.isStartElement()){
                            StartElement startElement = xmlEvent.asStartElement();
                            logger.info("StartElement: " + startElement.getName());
                        }
                        
                        
                    }
                }catch(XMLStreamException e){
                    e.printStackTrace();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
            
            //projectRepository.save(project);
            StringBuilder builder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader(upload.getInputstream(), StandardCharsets.UTF_8.name()))){
                int c = 0;
                while((c = reader.read()) != -1){
                    builder.append((char) c);
                }
            } catch (IOException ex) {
                logger.error("F:" + ex.getMessage());
            }
            selectedModell.setXml(builder.toString());
            modellRepository.save(selectedModell);
            viewNavigationHandler.navigateTo(Pages.NewProject.class);
        }
    }
 
    @PostConstruct
    public void init(){
        selectableModells = modellRepository.findAll();
        selectedModell = new Modell();
        selectableModells.add(selectedModell);
    }
    
}
