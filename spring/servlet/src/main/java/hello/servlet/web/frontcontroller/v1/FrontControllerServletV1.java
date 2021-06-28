package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑정보
    // 키 = url, 값 = controllerV1
    // 어떤 url로 호출이 되면 controllerV1으로 꺼내서 호출해
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 매핑정보를 서블릿이 생성될때 미리 담아놓기(저장해놓기) 그럼 나중에 꺼내 쓴다.
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 실제로 할때는 로그로 찍는 것이 좋음
        System.out.println("FrontControllerServletV1.service");

        // 위의 "/front-controller/v1/members/new-form" 이런거 얻어낼 수 있다.
        // 그럼 이게 키가 된다.
        String requestURI = request.getRequestURI();

        // controller 맵에서 꺼낸다. requestURI로 !! 그럼 controller가 찾아짐
        // 인터페이스로 꺼내게 되면 이 코드를 일관성있게 사용할 수 있다.
        // 부모(controller)는 자식을 받을 수 있다. requestURI
        ControllerV1 controller = controllerMap.get(requestURI);
        // 만약 없다면
        if (controller == null) {
            // 페이지가 없거나 못찾을때는 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 인터페이스 (process) 호출해주는거
        controller.process(request, response);
    }
}
