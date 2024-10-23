package com.bucarcep.teste.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bucarcep.teste.model.MessageResponse;
import com.bucarcep.teste.service.CepService;

@RestController
@RequestMapping("api/v1/cep")
public class CepController {
	
	private final CepService service;
	
	public CepController(CepService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/{cep}", consumes = "application/json; charset=UTF-8")
	public ResponseEntity<MessageResponse> buscarCep(@PathVariable String cep){
		try {
			service.buscarCep(cep);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Cep localizado: " + cep), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}