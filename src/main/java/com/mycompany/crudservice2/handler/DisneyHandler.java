/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudservice2.handler;

import com.mycompany.crudservice2.model.Shows;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author YASH
 */
public class DisneyHandler implements SOAPHandler<SOAPMessageContext> {
    
   public boolean handleMessage(SOAPMessageContext messageContext) {
//        check whether disney is present in incoming request in PArameter object if yes cancel request by returning false


//         boolean outbound = (boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
//            if(!outbound){
//                inbound(messageContext);
//            }else{
//                outbound(messageContext);  
//            }    
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }

    private void inbound(SOAPMessageContext messageContext) {
//        check whether disney is present in incoming request in PArameter object if yes cancel request by returning false
        try
        {
            if(messageContext.getMessage().getSOAPBody().getFirstChild().getLocalName().equals("insertVideo"))
            {
               Node s = (Node) messageContext.getMessage().getSOAPBody().getFirstChild().getFirstChild().getFirstChild();
                Node msg = (Node) messageContext.getMessage().getSOAPBody().getFirstChild().getFirstChild();
                msg.setTextContent("Humber");
                messageContext.getMessage().writeTo(System.out);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void outbound(SOAPMessageContext messageContext) {
        
    }
    
}
