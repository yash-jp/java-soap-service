/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudservice2.services;

import com.mycompany.crudservice2.controller.ShowsJpaController;
import com.mycompany.crudservice2.model.Shows;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author YASH
 */
@WebService(serviceName = "SOAPServices")
@HandlerChain(file = "SOAPServices_handler.xml")
public class SOAPServices {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
    ShowsJpaController showsJpaController = new ShowsJpaController(entityManagerFactory);
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    //    to get specific videos object thus this we can use to sow specific details of the video
    @WebMethod(operationName = "getShow")
    public Shows getShow(@WebParam(name = "id") String id) {
        Shows video = showsJpaController.findShows(BigDecimal.valueOf(Double.parseDouble(id)));
        return video;
    }

//    to get all videos
    @WebMethod(operationName = "getShows")
    public List<Shows> getShows() {
        List<Shows> videos = showsJpaController.findShowsEntities();
        return videos;
    }
    
//    only for testing
    public String method1(String param1){
        return param1;
    }
    
    
    @WebMethod(operationName="insertShow")
    public boolean insertShow(Shows video){
//        List<Shows> totalShows = getShows();
//        int size = totalShows.size()+1;
//        video.setId(BigDecimal.valueOf(size));
        try {
            showsJpaController.create(video);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SOAPServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @WebMethod(operationName="updateVideo")
    public boolean updateVideo(@WebParam(name = "video")Shows video){
        try {
            showsJpaController.edit(video);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SOAPServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    @WebMethod(operationName="deleteVideo")
    public boolean deleteVideo(@WebParam(name = "id")String id){
        try {
            showsJpaController.destroy(BigDecimal.valueOf(Double.parseDouble(id)));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SOAPServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
