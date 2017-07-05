/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblImage;
import Entities.TblUserInfo;
import Resources.Resource;
import Services.UploadImageService;
import Ultilities.XMLUltilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class UploadImageServlet extends HttpServlet {

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

        boolean multi = request.getParameter("multi").equals("true");
        try {
            HttpSession session = request.getSession(false);
            TblUserInfo currentUser = (TblUserInfo) session.getAttribute("user");
            if (currentUser == null) {
                response.sendError(403);
                return;
            }
            
            UploadImageService service = new UploadImageService();

            if (!multi) {
                Part image = request.getPart("image");
                TblImage tbl = service.UploadImage(image);
                if (tbl != null) {
                    response.getWriter().write("{ \"success\" : true, \"location\" : \"" + tbl.getLink() + "\", \"id\" : \"" + tbl.getId() + "\" }");
                } else {
                    response.getWriter().write("{ \"success\" : false, \"location\" : \"\", \"id\" : \"\"}");
                }
            } else {
                Collection<Part> images = request.getParts();
                List<TblImage> result = service.UploadMultipleImages(images);
                String tmp = "";
                for (TblImage p : result) {
                    tmp += " { \"id\" : \"" + p.getId() + "\", \"link\" : \"" + p.getLink() + "\" },";
                }
                if (!tmp.isEmpty()) tmp = tmp.substring(0, tmp.length() - 1);
                response.getWriter().write("{ \"success\" : true, \"data\" : [ " + tmp + " ] }");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
