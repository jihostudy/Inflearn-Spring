package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // #1. 예시
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","지호입니다"); // data에 hello를 넣으라는 뜻
        return "hello"; // templates의 hello.html로 가라는 뜻
    }

    // #2. 정적 페이지 방식
    // 문자열에 해당하는 View(template)가 존재하면, viewResolver가 view반환
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello-template";
    }

    // #3. API방식 (문자열 반환)
    // ResponseBody 사용시 viewResolver 대신, HttpMessageConverter가 작동
    // 문자열: StringConverter(StringHttpMessageConverter)
    @GetMapping("hello-string")
    @ResponseBody // HTTP의 body에 추가할 내용 (필수!)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // #4. API방식 (객체 반환)
    // 객체: JsonConverter(MappingJackson2HttpMessageConverter)가 JSON 형식으로 반환
    @GetMapping("hello-api")
    @ResponseBody // HTTP의 body에 추가할 내용 (필수!)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static  class Hello {
        private String name;
        // 단축키: Alt + Insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
