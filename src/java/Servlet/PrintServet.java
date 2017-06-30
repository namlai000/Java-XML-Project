/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Resources.Resource;
import Ultilities.XMLUltilities;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xmlgraphics.image.loader.ImageManager;
import org.apache.xmlgraphics.image.loader.impl.DefaultImageContext;

/**
 *
 * @author thegu
 */
public class PrintServet extends HttpServlet {

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
        response.setContentType("application/pdf;charset=UTF-8");
        try {
            String path = Resource.LOCATION_PATH;
            String xslPath = path + "WEB-INF/articleFO.xsl";
            String xmlPath = path + "WEB-INF/article.xml";
            String foPath = path + "WEB-INF/article.fo";
            String foConfig = path + "WEB-INF/fop.xconf";
            
            XMLUltilities.methodTrAX(xslPath, xmlPath, foPath, path);
            File f = new File(foPath);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FopFactory ff = FopFactory.newInstance();
            ff.setUserConfig(foConfig);
            ff.setBaseURL(path);
            FOUserAgent fua = ff.newFOUserAgent();
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, out);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            File fo = new File(foPath);
            Source src = new StreamSource(fo);
            SAXResult result = new SAXResult(fop.getDefaultHandler());
            trans.transform(src, result);
            
            byte[] content = out.toByteArray();
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
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
