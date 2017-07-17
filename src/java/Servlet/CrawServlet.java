/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblNews;
import Entities.TblNewsHeader;
import Resources.Resource;
import Ultilities.XMLUltilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author thegu
 */
public class CrawServlet extends HttpServlet {

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

        String link = request.getParameter("link");
        try {
            URL url = new URL(link);
            InputStream is = url.openConnection().getInputStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty(XMLInputFactory.IS_COALESCING, true);
            String output = processText(writer.toString().trim());
            StringReader fileReader = new StringReader(output);
            XMLStreamReader reader = factory.createXMLStreamReader(fileReader);

            boolean tittle = false;

            String content = "<p>";
            String a = output.substring(output.indexOf("<p class=\"Normal\">") + 18);
            String[] b = a.split("<p class=\"Normal\">");
            for (int i = 0; i < b.length; i++) {
                b[i] = b[i].substring(0, b[i].indexOf("</p>")).trim();
//                System.out.println("Text: " + b[i].trim());
                content += b[i] + "<br/><br/>";
            }
            content += "</p>";

            TblNewsHeader header = new TblNewsHeader();

            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (reader.getLocalName().equals("div") && reader.getAttributeValue(null, "class") != null && reader.getAttributeValue(null, "class").equals("title_news")) {
                            tittle = true;
                        } else if (reader.getLocalName().equals("h1") && tittle) {
                            header.setTittle(reader.getElementText().trim());
                            tittle = false;
                        } else if (reader.getLocalName().equals("h3") && reader.getAttributeValue(null, "class") != null && reader.getAttributeValue(null, "class").equals("short_intro txt_666")) {
                            header.setDescription(reader.getElementText().trim());
                        }
                        break;
                    default:
                        break;
                }

                reader.next();
            }
            reader.close();
            
            TblNews news = new TblNews();
            news.setContent(content);
            header.setTblNews(news);
            
            HttpSession session = request.getSession();
            session.setAttribute("crawl", header);

//            System.out.println("Content: " + content);
            // Output valdiated
//            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = dbfac.newDocumentBuilder();
//            Document document = builder.parse(new InputSource(new StringReader(output)));
//            NodeList nodeList = document.getElementsByTagName("*");
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                Node node = nodeList.item(i);
//                if (node.getNodeType() == Node.TEXT_NODE) {
//                    // do something with the current element
//                    System.out.println(node.getTextContent());
//                }
//            }
//            DOMSource source = new DOMSource(document);
//            FileWriter fr = new FileWriter(new File(Resource.LOCATION_PATH + "WEB-INF/crawldata.xml"));
//            StreamResult result = new StreamResult(fr);
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.transform(source, result);
        } catch (Exception e) {
            XMLUltilities.ExceptionLogging(e);
            
            HttpSession session = request.getSession();
            session.setAttribute("crawl", null);
        }

        response.sendRedirect(Resource.CrawlServlet_Page);
    }

    private String processText(String text) {
        text = text.replaceAll("&", "").trim();
        text = text.substring(text.indexOf("<body>"), text.indexOf("</body>") + 7).trim();

        while (text.contains("<script")) {
            String before = text.substring(0, text.indexOf("<script")).trim();
            String after = text.substring(text.indexOf("<script")).trim();
            after = after.substring(after.indexOf("</script>") + 9).trim();
            text = before + after;
        }
        while (text.contains("<ul")) {
            String before = text.substring(0, text.indexOf("<ul")).trim();
            String after = text.substring(text.indexOf("<ul")).trim();
            after = after.substring(after.indexOf("</ul>") + 5).trim();
            text = before + after;
        }
        while (text.contains("<input")) {
            String before = text.substring(0, text.indexOf("<input")).trim();
            String after = text.substring(text.indexOf("<input")).trim();
            after = after.substring(after.indexOf(">") + 1).trim();
            text = before + after;
        }
        while (text.contains("<img")) {
            String before = text.substring(0, text.indexOf("<img")).trim();
            String after = text.substring(text.indexOf("<img")).trim();
            after = after.substring(after.indexOf(">") + 1).trim();
            text = before + after;
        }
        while (text.contains("<select")) {
            String before = text.substring(0, text.indexOf("<select")).trim();
            String after = text.substring(text.indexOf("<select")).trim();
            after = after.substring(after.indexOf("</select>") + 9).trim();
            text = before + after;
        }
        while (text.contains("<a")) {
            String before = text.substring(0, text.indexOf("<a")).trim();
            String after = text.substring(text.indexOf("<a")).trim();
            after = after.substring(after.indexOf("</a>") + 4).trim();
            text = before + after;
        }

        text = text.replaceAll("<br>", "<br/>");
        text = text.replaceAll("\n", "");
        text = text.replaceAll("<em>", "");
        text = text.replaceAll("</em>", "");
        
        return text;
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
