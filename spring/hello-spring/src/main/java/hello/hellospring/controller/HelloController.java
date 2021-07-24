package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @RequestParam -> 파라미터를 받겠다
    // 위 "hello"와 다르게 웹사이트에서 url을 바꿔보자
    // Url에서 hello-mvc?name=spring 처럼 파라미터 넣어줘야한다.
    // 모델에 담으면 뷰에서 렌더링할때 사용
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // 파라미터에서 넘어온 name을 넘긴다. name이 spring으로 들어오면 그걸로 바뀌고 모델에 넘긴다
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // html body부분에 이 내용 직접 넣겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // "hello spring"
    }

    // 문자가 아니라 데이터 내놔
    @GetMapping("hello-api")
    @ResponseBody
    // 객체 만들기
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name); // 파라미터로 넘어온 name 넣기
        return hello; // 문자 아닌 객체를 넘긴다.
    }

    static class Hello {
        private String name;

        // 꺼낼 때는 getName, 넣을 때는 setName
        // get set 자바 빈 규약
        // property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
