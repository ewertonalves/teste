package com.bucarcep.teste.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bucarcep.teste.service.CepService;

@SpringBootTest
public class CepControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CepService cepService;

    @InjectMocks
    private CepController cepController;

    @Test
    public void testBuscarCep() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(cepController).build();

        String cep = "01001000";
        String respostaApi = "{\"cep\":\"01001-000\", \"logradouro\":\"Praça da Sé\"}";

        when(cepService.buscarCep(cep)).thenReturn(respostaApi);

        mockMvc.perform(get("/api/cep/" + cep)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(respostaApi));
    }
}