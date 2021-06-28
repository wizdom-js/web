package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 실제 뷰가 렌더링되도록 동작하는 것
    // 일단 서블릿에 있는거 그대로 가지고 온다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // dispatcher.foward 하면 자동으로 jsp가 렌더링
        dispatcher.forward(request, response);
    }
}
