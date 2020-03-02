/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.edu.jaxb.helpers;

import fpt.edu.jaxb.obj.Account;
import fpt.edu.jaxb.obj.Accounts;
import fpt.edu.jaxb.obj.Order;
import fpt.edu.jaxb.obj.OrderDetail;
import fpt.edu.jaxb.obj.OrderDetails;
import fpt.edu.jaxb.obj.Orders;
import java.io.File;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author huynh
 */
public class JAXBMarshallingManySchema {
    
    public static void main(String[] args) {
        OrderDetail detail1 = new OrderDetail();
        detail1.setId("00001");
        detail1.setProductId("P009");
        detail1.setPrice(23.4);
        detail1.setQuantity(new BigInteger("30"));
        detail1.setOrderId("OR001");
        
        OrderDetail detail2 = new OrderDetail();
        detail2.setId("00002");
        detail2.setProductId("P009");
        detail2.setPrice(2.5);
        detail2.setQuantity(new BigInteger("500"));
        detail2.setOrderId("OR001");
        
        OrderDetail detail3 = new OrderDetail();
        detail3.setId("00003");
        detail3.setProductId("P009");
        detail3.setPrice(88.2);
        detail3.setQuantity(new BigInteger("18"));
        detail3.setOrderId("OR001");
        
        OrderDetails details = new OrderDetails();
        details.getOrderDetail().add(detail1);
        details.getOrderDetail().add(detail2);
        details.getOrderDetail().add(detail3);
        
        Order order = new Order();
        order.setOrderId("OR001");
        order.setOrderDate("10/10/2020");
        order.setUserId("trihk");
        order.setIsDeliver(false);
        order.setAny(details);
        
        Orders orders = new Orders();
        orders.getOrder().add(order);
        
        Account account = new Account();
        account.setUsername("huynhkimtri");
        account.setFullname("Huynh Kim Tri");
        account.setIsAdmin(true);
        account.setPassword("123123123");
        account.setAny(orders);
        
        Accounts accounts = new Accounts();
        accounts.getAccount().add(account);
        
        try {
            JAXBContext context
                    = JAXBContext.newInstance(accounts.getClass(), orders.getClass(), details.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(accounts, new File("src/java/fpt/edu/jaxb/xsd/combine.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBMarshallingManySchema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
