package hello.servlet.web.servlet;

import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// http를 통해 servlet용 form을 볼 수 있다.
@WebServlet(name = "HttpFormServlet", urlPatterns = "/servlet/members/new-form")
public class HttpFormServlet extends HttpServlet {

    // 싱글톤이기때문에 new 안됨 !!
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 지금은 http응답으로 html이 나와야한다.
        // 그래서 content body 잡아준다.
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        // basic 패키지의 hello-form.html과 비슷한거. 그걸 자바 코드로 만든거얌
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                "    username: <input type=\"text\" name=\"username\" />\n" +
                "    age:      <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" + "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
