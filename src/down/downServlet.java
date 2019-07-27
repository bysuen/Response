package down;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "downServlet",urlPatterns = "/downServlet")
public class downServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得要下载的文件的名称
        String filename= request.getParameter("filename");

        //解决下载文件乱码问题
        //获得请求头中的User-Agent
        String agent = request.getHeader("User-Agent");
        //根据不同浏览器进行不同的编码

        String filenameEncoder = "";
        if (agent.contains("MSIE")){
            //IE浏览器
            filenameEncoder = URLEncoder.encode(filename,"utf-8");
            filenameEncoder = filenameEncoder.replace("+","");
        }else if (agent.contains("Firefox")){
            BASE64Encoder  base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?"
            +base64Encoder.encode(filename.getBytes("utf-8"))+"?-";
        }else {
            filenameEncoder=URLEncoder.encode(filename,"utf-8");
        }

        //要下载的文件的类型 客户端通过文件的MIME类型去区分
        response.setContentType(this.getServletContext().getMimeType(filename));
        //告诉客户端该文件不直接解析，直接以附件的形式下载
        response.setHeader("Content-Disposition","attachment;filename="+filenameEncoder);
        //获得文件的绝对路径
        String path= this.getServletContext().getRealPath("download/"+filename);
        //获得该文件的输入流
        InputStream in = new FileInputStream(path);
        //获得输出流--通过respon获得输出流。用于向客户端写内容
        ServletOutputStream out = response.getOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len=in.read(buffer))>0){
            out.write(buffer,0,len);
        }
        in.close();
        //out.close();

    }
}
