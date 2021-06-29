package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 어댑터의 역할이 핸들러를 호출해주고 결과를 반환타입을 mv에 맞춰서 반환해줘야한다.
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // handler가 controllerv3의 인스턴스인지 물어보는거.
        // controllerv3의 인터페이스를 구현한 뭔가가 넘어오게 되면 참 반환. 다른건 거짓반환
        return (handler instanceof ControllerV3);
    }

    @Override // handle은 실제 돌리는
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // handler를 object로 한 이유는 유연하게 하기 위해

        // controllerv3로 이 아이를 캐스팅 한다. 타입 변환해준다.
        ControllerV3 controller = (ControllerV3) handler;
        // 이 아이는 캐스팅해도 괜찮다. 왜냐면 위의 supports에서 controllerv3만 지원한다고 해줬기때문
        // 실제 호출이 될때 프론트 컨트롤러에서 supports랑 handle이 호출이 되는데
        // supports에서 걸러진 애를 찾아서 handle을 호출하기떄문에
        // 근데 여기서 handle은 v3라고 확정이 된거니까 캐스팅해서 쓰면 된다.

        // process 사용하려면 파라미터 map이 필요한데 그럼 파라미터 바꾸는거 필요하다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
