package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override //controller
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // controller에서 view로 이동할때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 서블릿에서 jsp 호출
        // dispatcher.forward는 다른 서블릿이나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출 발생
        dispatcher.forward(request, response);
    }
    // 고객 요청이 오면 위 전체가 호출이 된다.
    // 그럼 dispatcher.forward가 viewPath인 저 jsp를 다시 호출
    // 서버 내부에서 서버끼리 호출한다. 제어권을 넘겨준다.
}
