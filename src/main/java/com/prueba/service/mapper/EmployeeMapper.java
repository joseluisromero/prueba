package com.prueba.service.mapper;

import com.prueba.domain.Employee;
import com.prueba.service.dto.EmployeeRequest;
import com.prueba.service.dto.EmployeeResponse;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface EmployeeMapper {

    //aun no se  como probar este metodo
    @BeforeMapping
    default void calculeAgeReal(@MappingTarget EmployeeRequest employeeRequest) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(employeeRequest.getBirtDate());
        Period age = Period.between(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)), LocalDate.now());
        employeeRequest.setAge(age.getYears());
    }
    //aun no se  como probar este metodo
    @AfterMapping
    default void calculeAgeRealnext(@MappingTarget EmployeeResponse employeeResponse) {
        employeeResponse.setAge(employeeResponse.getAge() + 1);
    }
    //metodo  llmado antes de convertir el dto a entity (el parametros lo toma de ->source = "employeeRequest.birtDate")
    @Named("mapperAge")
    static Integer mapperAge(Date birtDate) {
        if (birtDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birtDate);
            Period ageCalculate = Period.between(LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)), LocalDate.now());
            return ageCalculate.getYears();
        }
        return 0;
    }


    @Mappings({
            @Mapping(target = "institutionName", source = "employee.institution.name"),
            @Mapping(target = "stature", source = "employee.stature"),
            //esta propiedad por default solo es  tomada si el campo es null  en caso contrario si es una cadena vacia tomara el valor que venga en el objeto
            @Mapping(target = "statusCivil", source = "employee.statusCivil", defaultExpression = "java(String.valueOf(\"SOLTERO\"))"),
            @Mapping(target = "dateAcceptation" ,source = "employee.dateAcceptation", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "createDate" ,source = "employee.createDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            //para poder coger el dato de un metodo publico mapeado en la entidad
            @Mapping(target = "ciNames" ,expression = "java(employee.getNombresCompletos())")
    })
    EmployeeResponse toEmployeeResponseStature(Employee employee);

    @Mappings({
            //para ignorar un  campo al momento de hacer el mapper
            @Mapping(target = "institution.employeeList" ,ignore = true),
            @Mapping(target = "institution", source = "employeeRequest.institutionDto"),
            //para llamar a un metodo aqui en la interface del mapper para que nos  calcule antes algun dato
            @Mapping(target = "age", source = "employeeRequest.birtDate", qualifiedByName = "mapperAge"),
            @Mapping(target = "statusCivil", source = "employeeRequest.statusCivil", qualifiedByName = "statusCivilDefault"),
            //Para enviarle un fecha en formato json y despues esta convertirla en Date antes de guardarla en DB
            @Mapping(target = "dateAcceptation" ,source = "employeeRequest.dateAcceptation", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            //Para setear la fecha de ahora con LocalDateTime mediante una expresion java
            @Mapping(target = "createDate" , expression = "java(LocalDateTime.now())")
    })
    Employee toEmployee(EmployeeRequest employeeRequest);

    @Named("statusCivilDefault")
    static String statusCivilDefault(String employeeRequest) {
        if (employeeRequest == null) {
            return "SOLTERO";
        }
        return null;
    }

}
