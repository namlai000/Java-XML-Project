/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblCategory;
import Entities.TblNewsHeader;
import Resources.Resource;
import Services.ExploreService;
import Ultilities.XMLUltilities;
import Wrapper.TblNewsHeaderWrapper;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author thegu
 */
public class ExploreServlet extends HttpServlet {

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

        List<TblNewsHeader> list = null;
        String menu = request.getParameter("menu");
        String page = request.getParameter("page");
        long i = 0;
        try {
            if (page == null || page.isEmpty() || !XMLUltilities.isInteger(page)) {
                page = "1";
            }
            if (menu == null) {
                menu = "1";
            }

            ExploreService service = new ExploreService();
            int p = Integer.parseInt(page);
            int m = Integer.parseInt(menu);

            list = service.GetNewsByPage(p, m);
            request.setAttribute("exploreList", list);

            i = service.GetNewsLength(m);
            request.setAttribute("pages", XMLUltilities.getPages((int) i));

            List<TblCategory> list2 = service.getAllCategories();
            request.setAttribute("menuList", list2);
        } catch (Exception e) {
            XMLUltilities.ExceptionLogging(e);
        }

        request.getRequestDispatcher(Resource.ExploreServlet_Page).forward(request, response);
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
