package com.prueba.service.impl;

import com.prueba.domain.Employee;
import com.prueba.repository.EmployeeRepository;
import com.prueba.service.EmployeeService;
import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import com.prueba.service.mapper.EmployeeAbstractMapper;
import com.prueba.service.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeAbstractMapper employeeAbstractMapper;

    @Override
    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        if (employeeRepository.findByIdentification(employeeRequest.getIdentification()).isPresent())
            log.error("El empleado ya existe");
        else {
            //employeeMapper.calculeAgeReal(employeeRequest);
            employeeRequest.setCreateDate(LocalDateTime.now());
            Employee employee=employeeMapper.toEmployee(employeeRequest);
            employee=employeeRepository.save(employee);
            return employeeMapper.toEmployeeResponseStature(employee);
        }
        return null;
    }
}
