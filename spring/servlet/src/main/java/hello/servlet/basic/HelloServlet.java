package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
// 서블릿은 먼저 httpservlet을 상속받아야함
public class HelloServlet extends HttpServlet {

    @Override  // 이 서블릿이 호출되면 서비스 메서드가 호출된다
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 쿼리 파라미터를 서블릿이 편하게 읽도록 지원.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답메세지 보내기
        response.setContentType("text/plain"); // 단순 문자
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username); // http 메세지 바디에 들어감
        
        
    }
}
