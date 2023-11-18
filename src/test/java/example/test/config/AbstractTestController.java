package example.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.test.member.controller.MemberAdvisorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith({ObjectMapperResolver.class, MockitoExtension.class})
public class AbstractTestController {

    protected ObjectMapper objectMapper;

    @BeforeEach
    void setup(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected MockMvc mockMvc(Object Controller) {
        return MockMvcBuilders.standaloneSetup(Controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new MemberAdvisorController())
//                .apply(documentationConfiguration(contextProvider))
                .build();
    }

    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
