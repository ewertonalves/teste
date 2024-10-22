package com.bucarcep.teste.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bucarcep.teste.client.CepClient;

@SpringBootTest
public class CepServiceTest {

	@Mock
	private CepClient cepClient;

	@Mock
	private LogService logService;

	@InjectMocks
	private CepService cepService;

	@Test
	public void testBuscarCep() {
		String cep = "01001000";
		String respostaApi = "{\"cep\":\"01001-000\", \"logradouro\":\"Praça da Sé\"}";
		Mockito.when(cepClient.buscarCep(cep)).thenReturn(respostaApi);
		String result = cepService.buscarCep(cep);
		assertEquals(respostaApi, result);
		verify(logService, times(1)).registrarLog(cep, respostaApi);
	}
}