package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // 매핑정보
    // 키 = url, 값 = controllerV1
    // 어떤 url로 호출이 되면 controllerV1으로 꺼내서 호출해
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 매핑정보를 서블릿이 생성될때 미리 담아놓기(저장해놓기) 그럼 나중에 꺼내 쓴다.
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 위의 "/front-controller/v1/members/new-form" 이런거 얻어낼 수 있다.
        // 그럼 이게 키가 된다.
        String requestURI = request.getRequestURI();

        // controller 맵에서 꺼낸다. requestURI로 !! 그럼 controller가 찾아짐
        // 인터페이스로 꺼내게 되면 이 코드를 일관성있게 사용할 수 있다.
        // 부모(controller)는 자식을 받을 수 있다. requestURI
        ControllerV2 controller = controllerMap.get(requestURI);
        // 만약 없다면
        if (controller == null) {
            // 페이지가 없거나 못찾을때는 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 인터페이스 (process) 참조값 호출해주는거
        // 그게 new MyView("경로");  임
        // controller는 더이상 호출하지 않고 객체만 생성해서 반환해준다.
        // 그럼 이 frontcontroller에서 공통 로직을 다 호출하고 처리한다.
        // 이게 왜 좋은 설계냐면 반환타입이 view이면 개발자들이 controller 만들때 view 반환해야하네
        // 라고 생각하게 된다.
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
