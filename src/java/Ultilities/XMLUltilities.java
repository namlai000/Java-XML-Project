/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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

        if (path != null) {
            trans.setParameter("url", path);
        }

        StreamSource xmlFile = new StreamSource(xmlPath);
        StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
        trans.transform(xmlFile, htmlFile);
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
}
