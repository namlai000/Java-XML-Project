/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblNewsHeader;
import Resources.Resource;
import Services.ArticleService;
import Services.MainService;
import Wrapper.TblNewsHeaderWrapper;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author thegu
 */
public class ArticleServlet extends HttpServlet {

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
            ArticleService service = new ArticleService();
            int number = Integer.parseInt(id);
            TblNewsHeader news = service.GetNewsById(number);
            if (news != null) {
                request.setAttribute("news", news);
                List<TblNewsHeader> ran3 = service.Random3NewsByCategories(news.getTblNewsList().get(0).getTblCategoryList());
                request.setAttribute("ran3", ran3);

                JAXBContext con = JAXBContext.newInstance(TblNewsHeader.class);
                Marshaller mar = con.createMarshaller();
                mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                StringWriter writer = new StringWriter();
                mar.marshal(news, writer);
                System.out.println(writer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(Resource.ArticleServlet_Page).forward(request, response);
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
