/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TblImage;
import Entities.TblRole;
import Entities.TblUser;
import Entities.TblUserInfo;
import Resources.Resource;
import Ultilities.CustomValidator;
import Ultilities.XMLUltilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author thegu
 */
public class UpdateProfileService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
    private EntityManager em = emf.createEntityManager();

    public TblImage UploadImage(Part image) throws FileNotFoundException, IOException {
        InputStream is = image.getInputStream();
        if (image.getContentType() != null || image.getSubmittedFileName() != null) {
            String fileName = "Images/" + XMLUltilities.Random128String() + "." + image.getContentType().replaceAll("image/", "");
            File targetFile = new File(Resource.LOCATION_PATH + "/" + fileName);
            System.out.println(targetFile.getAbsolutePath());
            OutputStream out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            out.write(buffer);
            is.close();
            out.close();

            TblImage tbl = new TblImage();
            tbl.setLink(fileName);

            em.getTransaction().begin();
            em.persist(tbl);
            em.getTransaction().commit();

            return tbl;
        }

        return null;
    }
}
