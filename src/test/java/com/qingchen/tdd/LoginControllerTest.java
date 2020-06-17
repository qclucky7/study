package com.qingchen.tdd;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional //支持数据回滚，避免测试数据污染环境
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void register() throws Exception {

        UserDTO userDTO = new UserDTO();

        String apiUrl = "/api/v1/register";

        MvcResult mvcResult = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post(apiUrl)  // 创建Post请求
                                .header("X-AUTH-TOKEN", "TESTTOKEN")
                                .contentType(MediaType.APPLICATION_JSON) // 设置请求的ContentType
                                .content(JSON.toJSONString(userDTO)) // 设置RequestBody JSON的必须指定ContentType
                                .accept(MediaType.APPLICATION_JSON) // 设置期望响应的类型
                )
                .andDo(print()) // 在请求过程中要进行的操作  这里输出状态 这里可以改造写入日志
                .andReturn();   // 发起Mock请求 拿到结果

        Assert.assertEquals(mvcResult.getResponse().getStatus(), MockHttpServletResponse.SC_OK);
        Assert.assertEquals(mvcResult.getResponse().getContentAsString(), "true");
    }

    @Test
    public void login() {
    }
}
