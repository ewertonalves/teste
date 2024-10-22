package com.bucarcep.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bucarcep.teste.model.ConsultaLog;

public interface LogRepository extends JpaRepository<ConsultaLog, Long> {
}