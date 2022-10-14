package com.prueba.service.impl;

import com.prueba.domain.Employee;
import com.prueba.repository.EmployeeRepository;
import com.prueba.service.EmployeeService;
import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import com.prueba.service.mapper.EmployeeAbstractMapper;
import com.prueba.service.mapper.EmployeeMapper;
import com.prueba.util.webclient.ApiWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeAbstractMapper employeeAbstractMapper;
    private final ApiWebClient apiWebClient;

    @Override
    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        if (employeeRepository.findByIdentification(employeeRequest.getIdentification()).isPresent())
            log.error("El empleado ya existe");
        else {
            //employeeMapper.calculeAgeReal(employeeRequest);
            employeeRequest.setCreateDate(LocalDateTime.now());
            Employee employee = employeeMapper.toEmployee(employeeRequest);
            employee = employeeRepository.save(employee);
            return employeeMapper.toEmployeeResponseStature(employee);
        }
        return null;
    }

    @Override
    public EmployeeResponse findByIdentification(String identification) {
        Optional<Employee> employeeOptional = employeeRepository.findByIdentification(identification);
        if (employeeOptional.isPresent())
            return employeeMapper.toEmployeeResponseStature(employeeOptional.get());
        else {
            log.error("El empleado no se encuentra registrado");
            return EmployeeResponse.builder().build();
        }
    }

    @Override
    public Mono<EmployeeResponse> createWebFlux(EmployeeRequest employeeRequest) {
        return apiWebClient.webClient()
                .post()
                .uri("/employee/create")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeRequest), EmployeeRequest.class)
                .retrieve()
                .bodyToMono(EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse findByPathVariable(String identification) {

        var employee = apiWebClient.webClient()
                .get()
                .uri("/employee/findByPathVariable/" + identification)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EmployeeResponse.class);
        return employee.block();
    }

    @Override
    public EmployeeResponse findByParam(String identification) {

        var employee = apiWebClient.webClient()
                .get()
                .uri("/employee/findByParam?identification="+identification)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(EmployeeResponse.class);
        return employee.block();
    }
}
