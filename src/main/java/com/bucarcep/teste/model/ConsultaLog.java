package com.bucarcep.teste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ConsultaLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cep;
	private String respostaApi;
	private LocalDateTime dataConsulta;

	public ConsultaLog(String cep, String respostaApi, LocalDateTime dataConsulta) {
		this.cep = cep;
		this.respostaApi = respostaApi;
		this.dataConsulta = dataConsulta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRespostaApi() {
		return respostaApi;
	}

	public void setRespostaApi(String respostaApi) {
		this.respostaApi = respostaApi;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	@Override
	public String toString() {
		return "ConsultaLog [id=" + id + ", cep=" + cep + ", respostaApi=" + respostaApi + ", dataConsulta="
				+ dataConsulta + "]";
	}

}