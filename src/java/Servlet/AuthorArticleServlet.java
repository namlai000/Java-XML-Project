/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblNewsHeader;
import Resources.Resource;
import Services.AuthorArticleService;
import Services.ViewService;
import Ultilities.XMLUltilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thegu
 */
public class AuthorArticleServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            if (id != null) {
                int i = Integer.parseInt(id);
                AuthorArticleService service = new AuthorArticleService();

                TblNewsHeader article = service.GetAuthorArticleById(i);
                if (article != null) {
                    XMLUltilities.JAXBMarshallerWithPath(article, Resource.LOCATION_PATH + "WEB-INF/authorarticle.xml", true);

                    List<TblNewsHeader> list = service.Random3Articles();
                    request.setAttribute("ran3", list);

                    HttpSession session = request.getSession(false);
                    Boolean hasViewed = (Boolean) session.getAttribute(article.getId().toString());
                    if (hasViewed == null || !hasViewed.booleanValue()) {
                        ViewService view = new ViewService();
                        view.IncreaseView(article.getId());
                        request.getSession().setAttribute(article.getId().toString(), true);
                    }
                }
            }
        } catch (Exception e) {
            XMLUltilities.ExceptionLogging(e);
        }

        RequestDispatcher rd = request.getRequestDispatcher(Resource.AuthorArticleServlet_Page);
        rd.forward(request, response);
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
