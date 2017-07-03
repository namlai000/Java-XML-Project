/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblNews;
import Entities.TblNewsHeader;
import Entities.TblUserInfo;
import Resources.Resource;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author thegu
 */
public class ProcessServlet extends HttpServlet {

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

        String url = Resource.MainServlet;
        
        try {
            Resource.LOCATION_PATH = getServletContext().getRealPath("/");

            String requestLocation = request.getParameter("location");
            if (requestLocation == null || requestLocation.isEmpty()) {
                url = Resource.MainServlet;
            } else if (requestLocation.equals("explore")) {
                url = Resource.ExploreServlet;
            } else if (requestLocation.equals("article")) {
                url = Resource.ArticleServlet;
            } else if (requestLocation.equals("author")) {
                url = Resource.AuthorServlet;
            } else if (requestLocation.equals("read")) {
                url = Resource.AuthorArticleServlet;
            } else if (requestLocation.equals("detail")) {
                url = Resource.AuthorDetailServlet;
            } else if (requestLocation.equals("login")) {
                url = Resource.LoginServlet;
            } else if (requestLocation.equals("loginPage")) {
                url = Resource.LoginServlet_Page;
            } else if (requestLocation.equals("search")) {
                url = Resource.SearchServlet;
            } else if (requestLocation.equals("comment")) {
                url = Resource.CommentServlet;
            } else if (requestLocation.equals("post")) {
                url = Resource.PostCommentServlet;
            } else if (requestLocation.equals("logout")) {
                url = Resource.LogoutServlet;
            } else if (requestLocation.equals("print")) {
                url = Resource.PrintServlet;
            } else if (requestLocation.equals("register")) {
                url = Resource.RegisterServlet;
            } else if (requestLocation.equals("profile")) {
                url = Resource.EditProfileServlet_Page;
            } else if (requestLocation.equals("editprofile")) {
                url = Resource.EditProfileServlet;
            } else if (requestLocation.equals("upload")) {
                url = Resource.UploadImageServlet;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
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
