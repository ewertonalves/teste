package com.bucarcep.teste.service;

import org.springframework.stereotype.Service;

import com.bucarcep.teste.client.CepClient;
import com.bucarcep.teste.serviceinterfaces.CepServiceInterface;

@Service
public class CepService implements CepServiceInterface{

    private final CepClient cepClient;
    private final LogService logService;
    
    public CepService(CepClient cepClient, LogService logService) {
    	this.cepClient = cepClient;
    	this.logService = logService;
    }

    public String buscarCep(String cep) {
        String respostaApi = cepClient.buscarCep(cep);
        logService.registrarLog(cep, respostaApi);
        return respostaApi;
    }
}