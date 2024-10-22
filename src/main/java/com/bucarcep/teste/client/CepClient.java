package com.bucarcep.teste.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CepCLient", url = "http://localhost:3000")
public interface CepClient {

	@GetMapping("/cep/{cep}")
	String buscarCep(@PathVariable("cep") String cep);
}
