/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultilities;

import Resources.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author thegu
 */
public class XMLUltilities {

    public static String JAXBMarshallerToString(Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter sw = new StringWriter();
        m.marshal(obj, sw);
        return sw.toString();
    }

    public static void JAXBMarshallerWithPath(Object obj, String path, boolean formatOutput) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatOutput);
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        m.marshal(obj, f);
    }

    public static void JAXBMarshallerToOutputStream(Object obj, OutputStream os) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(obj, os);
    }

    public static void methodTrAX(String xslPath, String xmlPath, String output, String path) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        StreamSource xslFile = new StreamSource(xslPath);
        Transformer trans = tf.newTransformer(xslFile);
        trans.setParameter("url", path);
        StreamSource xmlFile = new StreamSource(xmlPath);
        StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
        trans.transform(xmlFile, htmlFile);
    }

    public static Node CheckUsernameExists(String username, String xmlString) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        InputSource source = new InputSource(new StringReader(xmlString));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(source);

        XPathFactory fac = XPathFactory.newInstance();
        XPath path = fac.newXPath();
        String expression = "//TblUsers//TblUser[username='" + username + "']";

        Node result = (Node) path.evaluate(expression, document, XPathConstants.NODE);
        return result;
    }

    public static boolean isInteger(String number) {
        try {
            int i = Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static int[] getPages(int sizes) {
        int length;
        if (sizes % 10 == 0) {
            length = sizes / 10;
        } else {
            length = sizes / 10 + 1;
        }
        int[] pages = new int[length];
        int j = 1;
        for (int i = 0; i < length; i++) {
            pages[i] = j++;
        }
        return pages;
    }

    public static String Random128String() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(128);
    }

    public static void ExceptionLogging(Exception e) {
        try {
            System.err.println("ERROR LOGGED: " + e.getMessage());

            // Write to file
            FileWriter fw = new FileWriter(new File(Resource.LOCATION_PATH + "/../../" + "Exceptions." + new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()) + ".txt"), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            
            out.println("DATE: " + new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
            out.println("ERROR LEVEL: " + Level.SEVERE.getName());
            out.print("STACK TRACES: ");
            e.printStackTrace(out);
            out.println("------------------------------------------");
            
            out.close();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLUltilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
