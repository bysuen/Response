package Header;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//自动生成xml配置代码

public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 没有响应资源，并告知客户端去重定向到servlet2
        //设置状态码：302
        //      response.setStatus(302);
        //设置响应头Location
        //      response.setHeader("Location","/servlet2");

        //封装成一个重定向的方法sendRedirect(url)
        response.sendRedirect("/servlet2");
    }
}
