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
import reactor.core.publisher.Mono;

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

    /**
     * decide 1:Busqueda directa
     * decide 2:PathVariable
     * decide 3:Param
     *
     * @param identification
     * @return
     */
    @GetMapping(value = "/findBy/{decide}/{identification}")
    public ResponseEntity<EmployeeResponse> findByIdentification(@PathVariable(name = "decide") int decide, @PathVariable(name = "identification") String identification) {
        if (decide == 1)
            return new ResponseEntity<>(employeeService.findByIdentification(identification), HttpStatus.OK);
        else if (decide == 2)
            return new ResponseEntity<>(employeeService.findByPathVariable(identification), HttpStatus.OK);
        else if (decide == 3)
            return new ResponseEntity<>(employeeService.findByParam(identification), HttpStatus.OK);
        else
            return new ResponseEntity<>(EmployeeResponse.builder().build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByParam")
    public ResponseEntity<EmployeeResponse> findByParam(@RequestParam(name = "identification") String identification) {

        return new ResponseEntity<>(employeeService.findByIdentification(identification), HttpStatus.OK);
    }

    @GetMapping(value = "/findByPathVariable/{identification}")
    public ResponseEntity<EmployeeResponse> findByPathVariable(@PathVariable(name = "identification") String identification) {
        return new ResponseEntity<>(employeeService.findByIdentification(identification), HttpStatus.OK);
    }

    @GetMapping(value = "/json")
    public ResponseEntity<String> json() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        EmployeeRequest employeeRequest = EmployeeRequest.builder().id(1)
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

    @PostMapping(value = "/createWebFlux")
    public Mono<EmployeeResponse> createWebFlux(@RequestBody EmployeeRequest employeeRequest) {

        return employeeService.createWebFlux(employeeRequest);
    }


}
