package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 기존거와 차이는 object에 controllerv4가 들어왔는데 지금은 아무 컨트롤러나 들어가야 하므로 object쓴거
    // final은 지금 넣든 안넣든 크게 중요하지 않다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 어댑터가 여러개 있고 그중에서 찾아서 하나 꺼내써야하니까
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 위 두개에 값 넣어줘야함

    public FrontControllerServletV5() {
        // 매핑정보 넣기
        // v3여도 오류 안난다. 왜냐면 object니까
        initHandlerMappingMap();

        initHandlerAdapters();

    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());

    }

    private void initHandlerAdapters() {

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 메서드를 통해 깔끔하게 요청정보를 찾아서 handler를 가져오세요 알수있음(구체적인건 밑에)
        Object handler = getHandler(request); // MemberFormControllerV3 이거 반환
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 어댑터 목록을 뒤져서 맞는 핸들러 어댑터를 들고와야함
        // 단순하게 루프 돌려서 찾으면 된다
        MyHandlerAdapter adapter = getHandlerAdapter(handler); // ControllerV3HandlerAdapter 반환

        // 어댑터 호출
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();// 논리이름 new-form
        MyView view  = viewResolver(viewName); // 물리 이름이 된다.(메소드 뽑은거)

        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) { // 만약 어댑터가 handler를 지원하느냐
                return adapter;
            }
        } // ㅇㅖ외반환 (Argument가 잘못됐다)
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
