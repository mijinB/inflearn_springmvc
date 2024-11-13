package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody       // View 조회를 무시하고, HTTP message body 에 직접 해당 내용 입력
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String userName,     // 파라미터 이름으로 바인딩 (= request.getParameter("username"))
                                 @RequestParam("age") int age) {
        log.info("username={}, age={}", userName, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,     // HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {        // String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,      // null 과 ""은 다르기 때문에, 파라미터 이름만 있고 값이 없는 경우 빈문자로 통과 (?username=&age=20 통과)
                                       @RequestParam(required = false) Integer age) {       // int age -> null 을 int 에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는 defaultValue 사용) / 기본형인 int 는 null 이 들어갈 수 없고 Integer 은 객체이기 때문에 null 이 들어갈 수 있다.
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,    // defaultValue 는 빈 문자의 경우에도 설정한 기본 값이 적용된다.
                                       @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {             // 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자. / ex. param1=1,param1=2 같은 key 에 2개의 value
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }
}
