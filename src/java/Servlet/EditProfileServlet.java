/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblImage;
import Entities.TblRole;
import Entities.TblUser;
import Entities.TblUserInfo;
import Resources.Resource;
import Services.UpdateProfileService;
import Ultilities.CustomValidator;
import Ultilities.XMLUltilities;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *
 * @author thegu
 */
public class EditProfileServlet extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

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
//        CustomValidator validator = null;
        try {
            String schema = Resource.LOCATION_PATH + "WEB-INF/updateprofileSchema.xsd";

            HttpSession session = request.getSession(false);
            TblUserInfo currentUser = (TblUserInfo) session.getAttribute("user");
            if (currentUser == null) {
                response.sendError(403);
                return;
            }

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema sch = sf.newSchema(new File(schema));
            JAXBContext context = JAXBContext.newInstance(TblUserInfo.class);
            Unmarshaller u = context.createUnmarshaller();
            u.setSchema(sch);
            CustomValidator validator = new CustomValidator();
            u.setEventHandler(validator);

            TblUserInfo userinfo = (TblUserInfo) u.unmarshal(new InputSource(new StringReader(content)));

            if (validator.getError()) {
                String error = validator.errorMessage();
                response.getWriter().write("{ \"success\" : false , \"error\" : \"" + error + "\" }");
            } else {
                userinfo.setUserId(currentUser.getUserId());
                userinfo.setIDNumber(currentUser.getIDNumber());
                userinfo.setCreateDate(currentUser.getCreateDate());
                em.getTransaction().begin();
                if (userinfo.getImageID().getLink().isEmpty()) {
                    userinfo.setImageID(em.find(TblUserInfo.class, currentUser.getUserId()).getImageID());
                }
                em.merge(userinfo);
                em.getTransaction().commit();

                request.getSession().setAttribute("user", userinfo);
                request.getSession().setMaxInactiveInterval(5 * 60);

                response.getWriter().write("{ \"success\" : true }");
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
