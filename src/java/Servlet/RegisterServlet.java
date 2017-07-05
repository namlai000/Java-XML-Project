/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblRole;
import Entities.TblUser;
import Entities.TblUserInfo;
import Resources.Resource;
import Ultilities.CustomValidator;
import Ultilities.XMLUltilities;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *
 * @author thegu
 */
public class RegisterServlet extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String content = request.getParameter("content").trim();
        CustomValidator validator = null;
        try {
            em = emf.createEntityManager();
            
            String path = Resource.LOCATION_PATH + "WEB-INF/registerSchema.xsd";
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(path));
            JAXBContext context = JAXBContext.newInstance(TblUserInfo.class);
            Unmarshaller u = context.createUnmarshaller();
            u.setSchema(schema);
            validator = new CustomValidator();
            u.setEventHandler(validator);

            TblUserInfo userinfo = (TblUserInfo) u.unmarshal(new InputSource(new StringReader(content)));

            if (validator.getError()) {
                String error = validator.errorMessage();
                response.getWriter().write("{ \"success\" : false , \"error\" : \"" + error + "\" }");
            } else {
                userinfo.setCreateDate(Calendar.getInstance().getTime());
                TblUser user = userinfo.getTblUser();
                user.setRole(em.find(TblRole.class, Resource.ROLE_NORMALUSER));

                String list = (String) request.getAttribute("loginList");
                Node node = XMLUltilities.CheckUsernameExists(user.getUsername(), list);
                if (node == null) {
                    em.getTransaction().begin();
                    em.persist(user);
                    em.flush();
                    userinfo.setUserId(user.getId());
                    em.persist(userinfo);
                    em.flush();
                    em.getTransaction().commit();

                    request.getSession().setAttribute("user", userinfo);
                    request.getSession().setMaxInactiveInterval(60 * 60);

                    response.getWriter().write("{ \"success\" : true }");
                } else {
                    response.getWriter().write("{ \"success\" : false , \"error\" : \"This username has already existed!\" }");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{ \"success\" : false , \"error\" : \"" + e.getMessage() + "\" }");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
