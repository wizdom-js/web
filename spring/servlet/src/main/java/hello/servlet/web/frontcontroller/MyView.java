package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 실제 뷰가 렌더링되도록 동작하는 것
    // 일단 서블릿에 있는거 그대로 가지고 온다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // dispatcher.forward 하면 자동으로 jsp가 렌더링
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 모델에 있는거 다 꺼내기
        // 자바8 문법 / 변수명 key value 이름으로해서 map의 루프를 돌리는거
        // request.setA~ 에 key value 다 담아놓는거임
        // 메소드 뽑기 전
        // model.forEach((key, value) -> request.setAttribute(key, value));
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
