package com.devtty.vmo.view;

import com.devtty.vmo.model.Project;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
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
            
        }
    }
    
}
