/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblUser;
import Services.LoginService;
import Ultilities.XMLUltilities;
import Wrapper.TblUserWrapper;
import java.util.List;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author thegu
 */
public class RequestServletListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        try {
            LoginService service = new LoginService();
            List<TblUser> users = service.GetAllLogins();
            String result = XMLUltilities.JAXBMarshallerToString(new TblUserWrapper(users));
            sre.getServletRequest().setAttribute("loginList", result);
            System.out.println("Request initialized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
