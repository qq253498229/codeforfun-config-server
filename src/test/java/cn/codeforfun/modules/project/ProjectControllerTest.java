package cn.codeforfun.modules.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
class ProjectControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    void list() throws Exception {
        mockMvc.perform(
                get("/project")
        )
                .andExpect(status().isOk())
//                .andDo(print())
        ;
    }
}