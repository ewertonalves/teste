package com.bucarcep.teste.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bucarcep.teste.model.ConsultaLog;
import com.bucarcep.teste.repository.LogRepository;
import com.bucarcep.teste.serviceinterfaces.LogServiceInterface;

@Service
public class LogService implements LogServiceInterface {

	private final LogRepository logRepository;

	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	public void registrarLog(String cep, String respostaApi) {
		ConsultaLog log = new ConsultaLog(cep, respostaApi, LocalDateTime.now());
		logRepository.save(log);
	}
}