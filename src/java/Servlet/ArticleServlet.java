/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Entities.TblComment;
import Entities.TblNews;
import Entities.TblNewsHeader;
import Entities.TblUserInfo;
import Resources.Resource;
import Services.ArticleService;
import Services.CommentService;
import Services.MainService;
import Ultilities.XMLUltilities;
import Wrapper.TblCommentWrapper;
import Wrapper.TblNewsHeaderWrapper;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
                List<TblNewsHeader> ran3 = service.Random3NewsByCategories(news.getTblNews().getCatID().getId());
                request.setAttribute("ran3", ran3);
            }

//            EntityManagerFactory emf = Persistence.createEntityManagerFactory(Resource.Persistence);
//            EntityManager em = emf.createEntityManager();
//
//            TblNewsHeader head = new TblNewsHeader();
//            head.setTittle("Nhan sắc diễn viên Xa Thi Mạn qua thời gian");
//            head.setDescription("Ở tuổi 42, người đẹp \"Ỷ thiên đồ long ký\" vẫn giữ được gương mặt trẻ trung, vóc dáng thon gọn. ");
//            head.setDate(Calendar.getInstance().getTime());
//
//            TblNews news2 = new TblNews();
//            news2.setAuthorID(em.find(TblUserInfo.class, 3));
//            news2.setContent("Theo trang Stuff, phần ba của loạt phim ăn khách Planet of the Apes được dự đoán tạo tiếng vang hè này. War for the Planet of the Apes tiếp nối nội dung của phần đầu (2011) và phần hai (2014), với đỉnh điểm là xung đột giữa loài khỉ (Caesar dẫn đầu) và lực lượng quân đội (do Colonel lãnh đạo). Trên Metacritic, phim giành được 79% nhận xét tích cực trong số 18 bài đánh giá. Với Rotten Tomatoes, phim nhận 96% phản hồi tích cực.\n"
//                    + "\n"
//                    + "Cây bút Bilge Ebiri của tờ Village Voice cho rằng đây là bom tấn quan trọng nhất năm. \"Hiệu ứng đáng kinh ngạc, hành động thú vị, âm nhạc tuyệt vời và Andy Serkis một lần nữa thể hiện nhân vật khỉ thông qua công nghệ nắm bắt chuyển động rất chân thật\", Bilge Ebiri viết.\n"
//                    + "\n"
//                    + "Chris Nashawaty, nhà phê bình của Entertainment Weekly, so sánh: \"Giống như Caesar và đồng loại trong phim, tác phẩm dường như ngày càng thông minh và đầy tính con người khi tiến hóa\". Trong khi đó, Peter Bradshaw của The Guardian mô tả phần ba của loạt phim là \"một cuộc phiêu lưu sâu sắc\". Trên IndieWire, War for the Planet of the Apes được đánh giá cao nhờ kết hợp giữa công nghệ và cách kể chuyện cuốn hút. \"Bộ ba tác phẩm khoa học viễn tưởng hay nhất kể từ thời Star Wars\", trang IndieWire nhấn mạnh.\n"
//                    + "\n"
//                    + "Cây viết Kristopher Tapley của Variety kêu gọi Viện Hàn Lâm khôi phục giải thưởng \"Thành tựu đặc biệt\" để dành cho War for the Planet of the Apes. Lần cuối cùng giải Oscar này được trao là vào năm 1996, cho những nhà sản xuất của Pixar với tác phẩm Toy Story.");
//                        
//            em.getTransaction().begin();
//            
//            em.persist(news2);
//            em.flush();
//            head.setId(news2.getHeaderID());
//            em.persist(head);
//            em.flush();
//            
//            em.getTransaction().commit();
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
