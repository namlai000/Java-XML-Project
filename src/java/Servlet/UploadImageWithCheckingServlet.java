/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblImage;
import Entities.TblUserInfo;
import Services.UploadImageService;
import Ultilities.XMLUltilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author thegu
 */
public class UploadImageWithCheckingServlet extends HttpServlet {

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

        try {
            HttpSession session = request.getSession(false);
            TblUserInfo currentUser = (TblUserInfo) session.getAttribute("user");
            if (currentUser == null) {
                response.sendError(403);
                return;
            }

            UploadImageService service = new UploadImageService();

            List<TblImage> result = new ArrayList<>();

            String[] strings = request.getParameterValues("image");
            Collection<Part> images = request.getParts();

            int count = 0;
            for (Part i : images) {
                if (i.getContentType() == null || i.getSubmittedFileName() == null) {
                    String f = null;
                    TblImage img = service.GetImageByLink(strings[count++]);
                    result.add(img);
                } else {
                    TblImage img = service.UploadImage(i);
                    result.add(img);
                }
            }
            
            String tmp = "";
            for (TblImage p : result) {
                tmp += " { \"id\" : \"" + p.getId() + "\", \"link\" : \"" + p.getLink() + "\" },";
            }
            if (!tmp.isEmpty()) {
                tmp = tmp.substring(0, tmp.length() - 1);
            }
            response.getWriter().write("{ \"success\" : true, \"data\" : [ " + tmp + " ] }");
        } catch (Exception e) {
            XMLUltilities.ExceptionLogging(e);
            
            response.getWriter().write("{ \"success\" : false }");
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
