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
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 *
 * @author thegu
 */
public class RequestServletListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        try {
            LoginService service = new LoginService();
            List<TblUser> users = service.GetAllLogins();
            String result = XMLUltilities.JAXBMarshallerToString(new TblUserWrapper(users));
            sre.getServletRequest().setAttribute("loginList", result);
        } catch (Exception e) {
            XMLUltilities.ExceptionLogging(e);
        }
    }

}
