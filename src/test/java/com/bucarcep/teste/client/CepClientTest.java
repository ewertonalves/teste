package com.bucarcep.teste.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepClientTest {

	@Mock
	private CepClient cepClient;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarCep() {
		String cep = "01001-000";
		String mockResponse = "{ \"cep\": \"01001-000\", \"logradouro\": \"Praça da Sé\", \"bairro\": \"Sé\", \"cidade\": \"São Paulo\", \"estado\": \"SP\" }";

		when(cepClient.buscarCep(cep)).thenReturn(mockResponse);
		String response = cepClient.buscarCep(cep);

		assertNotNull(response);
		assertEquals(mockResponse, response);
		verify(cepClient, times(1)).buscarCep(cep);
	}
}
