package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,                   // MAP 과 유사한데, 하나의 키에 여러 값을 받을 수 있다. HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {       // @CookieValue 의 value 가 cookie 이름
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}

/*
 * @Controller 의 사용 가능한 파라미터 목록을 조회하는 공식 매뉴얼 주소
 *  : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html
 * @Controller 의 사용 가능한 응답 값 목록을 조회하는 공식 매뉴얼 주소
 *  : https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html
 */
