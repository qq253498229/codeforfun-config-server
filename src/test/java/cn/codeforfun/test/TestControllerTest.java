package cn.codeforfun.test;

import cn.codeforfun.generator.mapper.ApplicationMapper;
import cn.codeforfun.generator.model.Application;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
class TestControllerTest {
    @Resource
    private MockMvc mockMvc;
    @Resource
    private ApplicationMapper applicationMapper;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 15; i++) {
            Application application = Application.builder().applicationName("app" + i).applicationCode("app" + i).projectId(1L).build();
            applicationMapper.insertSelective(application);
        }
    }

    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test")
                .param("page", "1")
                .param("size", "5")
                .param("sort", "application_name,asc")
                .param("sort", "application_code,desc")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.list").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.list", Matchers.hasSize(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageNum").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize").value(5))
        ;
    }
}