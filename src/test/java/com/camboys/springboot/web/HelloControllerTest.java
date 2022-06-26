package com.camboys.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) //스프링부트테스트와 Junit사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class) // web(spring mvc)에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 bean을 주입 받음
    private MockMvc mvc; //웹 api를 테스트 할 때 사용. 스프링 mvc 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello ="hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 //hello 주소로 http get 요청을 함
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증. 컨트롤러에서 'hello'를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name", name).param("amount", String.valueOf(amount))) //param: api 테스트 할때 사용될 요청 파라미터 설정
                        .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name))) //jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
