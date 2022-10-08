package com.prueba.controller;

import com.prueba.domain.Employee;
import com.prueba.domain.enums.GeneroType;
import com.prueba.service.EmployeeService;
import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import com.prueba.service.dto.InstitutionDto;
import com.prueba.util.JsonConverterGson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/create")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest employeeRequest) {

        return new ResponseEntity<>(employeeService.create(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping(value = "/json")
    public ResponseEntity<String> json() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        EmployeeRequest employeeRequest=EmployeeRequest.builder().id(1)
                .identification("1311901001")
                .firstName("Jose")
                .lastName("Romero")
                .age(36)
                .birtDate(format.parse("1986-11-28"))
                .createDate(LocalDateTime.now())
                .generoType(GeneroType.MASCULINE)
                .surnames("Jose Romero")
                .institutionDto(InstitutionDto.builder().id(1)
                        .name("Banco Pichincha").build())
                .build();
        return new ResponseEntity<String>(JsonConverterGson.objectToJson(employeeRequest), HttpStatus.OK);
    }

    /*@GetMapping(value = "/person/{name}")
    public ResponseEntity<List<Person>> getTravelsForPerson(@PathVariable(name = "name") String name) {

        return new ResponseEntity<List<Person>>(lambdasService.getTravelsForPersonMap(name), HttpStatus.OK);
    }*/


}
