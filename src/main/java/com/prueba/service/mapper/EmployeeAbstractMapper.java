package com.prueba.service.mapper;

import com.prueba.domain.Employee;
import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

@Mapper(componentModel = "spring")
public abstract class EmployeeAbstractMapper {
    /*
    @BeforeMapping
    public  void calculeAgeReal( @MappingTarget Employee employee,   EmployeeRequest employeeRequest) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(employeeRequest.getBirtDate());
        Period age = Period.between(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)), LocalDate.now());
        employee.setAge(age.getYears());
        employeeRequest.setAge(age.getYears());
    }

    @AfterMapping
    public void calculeAgeRealnext(@MappingTarget EmployeeResponse employeeResponse) {
        employeeResponse.setAge(employeeResponse.getAge() + 1);
    }

    @Mappings({
            @Mapping(target = "institutionName", source = "employee.institution.name")
    })
    abstract EmployeeResponse toEmployeeResponse(Employee employee);

    @Mappings({
            @Mapping(target = "institutionName", source = "employee.institution.name"),
            @Mapping(target = "stature", source = "employee.stature"),
            @Mapping(target = "statusCivil", source = "employee.statusCivil", defaultExpression = "java(String.valueOf(\"SOLTERO\"))")
    })
    public abstract EmployeeResponse toEmployeeResponseStature(Employee employee);

    @Mappings({
            @Mapping(target = "institution.employeeList" ,ignore = true),
            @Mapping(target = "institution", source = "employeeRequest.institutionDto")

    })
    public abstract Employee toEmployee(EmployeeRequest employeeRequest);
*/
}
