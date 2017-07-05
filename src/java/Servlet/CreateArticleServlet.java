/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblImage;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Entities.TblSubCategory;
import Entities.TblUserInfo;
import Resources.Resource;
import Ultilities.CustomValidator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
import org.xml.sax.InputSource;

/**
 *
 * @author thegu
 */
public class CreateArticleServlet extends HttpServlet {

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

        String data = request.getParameter("content");
        try {
            em = emf.createEntityManager();
            
            HttpSession session = request.getSession(false);
            TblUserInfo currentUser = (TblUserInfo) session.getAttribute("user");
            if (currentUser == null) {
                response.sendError(403);
                return;
            }

            String path = Resource.LOCATION_PATH + "WEB-INF/createNews.xsd";
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(path));
            JAXBContext context = JAXBContext.newInstance(TblNewsHeader.class);
            Unmarshaller u = context.createUnmarshaller();
            u.setSchema(schema);
            CustomValidator validator = new CustomValidator();
            u.setEventHandler(validator);
            System.out.println(data);

            TblNewsHeader newsheader = (TblNewsHeader) u.unmarshal(new InputSource(new StringReader(data)));
//
            if (validator.getError()) {
                response.getWriter().write("{ \"success\" : false , \"error\" : \"" + validator.errorMessage() + "\" }");
            } else {
                newsheader.setDate(Calendar.getInstance().getTime());

                TblNews news2 = newsheader.getTblNews();

                TblSubCategory sub = news2.getCatID();
                if (sub != null) {
                    news2.setCatID(em.find(TblSubCategory.class, sub.getId()));
                }

                List<TblImage> images = news2.getTblImageList();
                news2.setAuthorID(currentUser);
                if (images != null) {
                    for (TblImage i : images) {
                        List<TblNews> tmp = new ArrayList<TblNews>();
                        tmp.add(news2);
                        i.setTblNewsList(tmp);
                    }
                }

                em.getTransaction().begin();

                em.persist(news2);
                em.flush();
                newsheader.setId(news2.getHeaderID());
                em.persist(newsheader);
                em.flush();

                if (images != null) {
                    for (TblImage i : images) {
                        em.persist(i);
                        em.flush();
                    }
                }

                em.getTransaction().commit();

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
