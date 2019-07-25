package PrintServlet;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class PrintServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     /*   String data = "sdsdss";
        //获取字节输出流对象
        OutputStream out = response.getOutputStream();
        //输出信息
        out.write(data.getBytes());*/

     //手动设置响应行中的状态码
        response.setStatus(302);
        //设置响应头
        Date date = new Date();
        response.addHeader("name","sds");
        response.addIntHeader("age",89);
        response.addDateHeader("birthday",date.getTime());



    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
