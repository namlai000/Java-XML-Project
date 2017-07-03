/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblImage;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Entities.TblUserInfo;
import Resources.Resource;
import Services.CommentService;
import Ultilities.XMLUltilities;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;

/**
 *
 * @author thegu
 */
public class CreateArticleServlet extends HttpServlet {

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

        String data = request.getParameter("content");
        try {
            HttpSession session = request.getSession(false);
            TblUserInfo user = (TblUserInfo) session.getAttribute("user");
            if (user != null) {
                //            String path = Resource.LOCATION_PATH + "WEB-INF/registerSchema.xsd";
//            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            Schema schema = sf.newSchema(new File(path));
                JAXBContext context = JAXBContext.newInstance(TblNewsHeader.class);
                Unmarshaller u = context.createUnmarshaller();
//            u.setSchema(schema);
//            validator = new CustomValidator();
//            u.setEventHandler(validator);
                System.out.println(data);
                TblNewsHeader newsheader = (TblNewsHeader) u.unmarshal(new InputSource(new StringReader(data)));
                String a = newsheader.getTblNews().getContent();
//
                newsheader.setDate(Calendar.getInstance().getTime());

                TblNews news2 = newsheader.getTblNews();
                List<TblImage> images = news2.getTblImageList();
                news2.setAuthorID(user);
                for (TblImage i : images) {
                    List<TblNews> tmp = new ArrayList<TblNews>();
                    tmp.add(news2);
                    i.setTblNewsList(tmp);
                }

                em.getTransaction().begin();

                em.persist(news2);
                em.flush();
                newsheader.setId(news2.getHeaderID());
                em.persist(newsheader);
                em.flush();
                for (TblImage i : images) {
                    em.persist(i);
                    em.flush();
                }
                
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{ \"success\" : false , \"error\" : \"" + e.getMessage() + "\" }");
        }

        response.getWriter().write("{ \"success\" : true , \"error\" : \"none\" }");
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
