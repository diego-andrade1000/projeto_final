package com.example.backend;


import com.example.backend.Domain.Estoque;
import com.example.backend.Repository.EstoqueRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.ClassRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(
        classes = PadariaPaoQuentinhoAplication.class,
        properties = {
                "execution.isolation.strategy=SEMAPHORE",
                "hystrix.shareSecurityContext=true"
        }
)
@Transactional
@WithMockUser
public class BackendApplicationTests {

    private static final ObjectMapper mapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EstoqueRepository estoqueRepository;


    @ClassRule
    public static WireMockRule WIREMOCK = new WireMockRule(wireMockConfig().dynamicPort());


    @Test
    @DisplayName("Deve ser possível criar um produto")
    public void criarProduto() throws Exception {
        var produto = new Estoque("produto", 4, "50");
        mockMvc.perform(post("/api/estoque/")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(produto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

