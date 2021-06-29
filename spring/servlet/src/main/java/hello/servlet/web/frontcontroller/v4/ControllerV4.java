package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {


    /**
     *
     * @param paraMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paraMap, Map<String, Object> model);
    // model에서 키는 string이고 값은 object(아무거나 넣을 수 있다는 의미)
}
