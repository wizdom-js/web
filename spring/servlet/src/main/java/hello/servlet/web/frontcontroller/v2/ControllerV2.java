package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // v1과 다른건 myview로 반환한다는 것
    // 기존에는 void였고 controller가 알아서 다 forward로 이동했는데
    // (controller로 이동한다음에 jsp이동하고 이런거)
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
