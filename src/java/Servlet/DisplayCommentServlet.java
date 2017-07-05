/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblComment;
import Entities.TblCommentCustom;
import Resources.Resource;
import Services.CommentService;
import Ultilities.XMLUltilities;
import Wrapper.TblCommentWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thegu
 */
public class DisplayCommentServlet extends HttpServlet {

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
        response.setContentType("application/xml;charset=UTF-8");
        try {
            String newId = request.getParameter("id");
            if (XMLUltilities.isInteger(newId)) {
                int id = Integer.parseInt(newId);
                CommentService service = new CommentService();
                List<TblComment> list = service.getCommentsByNewId(id);
                List<TblCommentCustom> tmp = new ArrayList<>();
                for (TblComment c : list) {
                    TblCommentCustom cus = new TblCommentCustom();
                    cus.setContent(c.getContent());
                    cus.setFullname(c.getUserID().getLastname());
                    cus.setImageLink(c.getUserID().getImageID() == null ? Resource.DefaultUserAvatar : c.getUserID().getImageID().getLink());
                    tmp.add(cus);
                }
                TblCommentWrapper wrap = new TblCommentWrapper(tmp);
                XMLUltilities.JAXBMarshallerToOutputStream(wrap, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
