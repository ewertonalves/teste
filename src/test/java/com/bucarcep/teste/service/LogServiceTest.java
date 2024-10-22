package com.bucarcep.teste.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bucarcep.teste.model.ConsultaLog;
import com.bucarcep.teste.repository.LogRepository;

@SpringBootTest
public class LogServiceTest {

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LogService logService;

	@Test
	public void testRegistrarLog() {
		String cep = "01001000";
		String respostaApi = "{\"cep\":\"01001-000\", \"logradouro\":\"Praça da Sé\"}";
		LocalDateTime now = LocalDateTime.now();
		logService.registrarLog(cep, respostaApi);
		verify(logRepository, times(1)).save(Mockito.any(ConsultaLog.class));
	}
}