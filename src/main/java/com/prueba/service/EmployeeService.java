package com.prueba.service;

import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest employeeRequest);
    Mono<EmployeeResponse> createWebFlux(EmployeeRequest employeeRequest);
    EmployeeResponse findByPathVariable(String identification);
    EmployeeResponse findByParam(String identification);
    EmployeeResponse findByIdentification(String identification);
}
