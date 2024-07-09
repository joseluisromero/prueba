package com.prueba.controller;

import com.prueba.domain.Car;
import com.prueba.domain.FormatoFechaRequest;
import com.prueba.service.CarService;
import com.prueba.service.dto.AdditionalClientInfo;
import com.prueba.service.dto.AdditionalRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final CarService carService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Set<Car> obtener() {
        log.error("Error");
        Set<Car> listaCar = carService.getlistCar();
        System.out.println("lista: " + listaCar.size());
        return listaCar;
    }

    @PostMapping("/additional-request")
    public ResponseEntity<?> saveAdditionalRequest(@RequestBody AdditionalRequestDto additionalRequestDto) {
        try {
            AdditionalRequestDto additionalRequest = new AdditionalRequestDto();
            additionalRequest.setAdditionalFirstName("SAEL");
            additionalRequest.setAdditionalSecondName("JECSAN");
            additionalRequest.setAdditionalFirstSurname("CEDEÑO");
            additionalRequest.setAdditionalSecondSurname("ROMERO");
            additionalRequest.setAdditionalIdentification("1311901001");
            additionalRequest.setAdditionalSex("MASCULINO");
            additionalRequest.setAdditionalDateOfBirth(Timestamp.valueOf("2000-04-23 00:00:00"));
            additionalRequest.setAdditionalCivilStatus("SOLTERO");
            additionalRequest.setAdditionalRelationship("SOBRINO");
            additionalRequest.setAdditionalPhoneNumber("0312321");
            additionalRequest.setAdditionalEmail("manabis.rum@gmail.com");
            additionalRequest.setAdditionalLimit(10500);
            additionalRequest.setAdditionalWrittenName("prueba nuevo  micro");
            additionalRequest.setCreatedTime(new Date());
            additionalRequest.setCreationSent(additionalRequestDto.isCreationSent());
            additionalRequest.setNotificationSent(additionalRequestDto.isNotificationSent());
            AdditionalClientInfo clientInfo = new AdditionalClientInfo();
            //clientInfo.setId(new Long(18));
            clientInfo.setIdentification("1723263941");
            clientInfo.setUnmaskedIdentification("1723263941");
            clientInfo.setCif("5676052");
            clientInfo.setIdentificationHash("hashEjemplo3");
            clientInfo.setFirstName("LEILA");
            clientInfo.setSecondName("DEL PILAR");
            clientInfo.setFirstSurname("PALACIOS");
            clientInfo.setCardType("MSC BLACK RECOMPENSAS BP");
            clientInfo.setCardProgram("Pichincha Miles");
            clientInfo.setCardName("Mastercard Black Pichincha Miles");
            clientInfo.setPhoneNumber("0987641687");
            clientInfo.setAddress("joromero@pichincha.com");
            clientInfo.setEmail("joromero@pichincha.com");

            additionalRequest.setClient(clientInfo);
            //AdditionalRequestResponseDto additionalRequestResponseDto = additionalRequestService.save(additionalRequest);
            return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
            //return null;
        } catch (Throwable th) {
            throw th;
        }

    }

    @PostMapping("/fecha")
    public void fecha(@RequestBody FormatoFechaRequest formatoFechaRequest) {
        //Date date=formatoFechaRequest.getFechaUtil();
        System.out.println("date {} " + formatoFechaRequest.toString());
    }

    @GetMapping("/fechaTrama")
    public String fechaTrama(String fecha) {
        System.out.println("fecha:" + fecha);
        Timestamp date = Timestamp.valueOf(fecha);
        Integer year = date.toLocalDateTime().getYear();
        Integer month = date.toLocalDateTime().getMonth().getValue();
        Integer day = date.toLocalDateTime().getDayOfMonth();
        return year + "" + String.format("%02d", month) + "" + String.format("%02d", day);
    }

    @GetMapping("/validAge")
    public Integer validAge(String fecha) {
        System.out.println("fecha:" + fecha);
        Timestamp date = Timestamp.valueOf(fecha);
        Integer year = date.toLocalDateTime().getYear();
        Integer month = date.toLocalDateTime().getMonth().getValue();
        Integer day = date.toLocalDateTime().getDayOfMonth();
        Period edad = Period.between(LocalDate.of(year, month, day), LocalDate.now());
        System.out.println(String.format("%d años, %d meses y %d días",
                edad.getYears(),
                edad.getMonths(),
                edad.getDays()));
        return edad.getYears();
    }

    @GetMapping("/switch")
    public boolean switchEjemplo(@RequestParam String status, @RequestParam String channel) {
        return isSend(status, channel);
    }

    @GetMapping("/day")
    public boolean day() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dia = c.get(Calendar.DAY_OF_WEEK);
        if (dia == Calendar.SUNDAY) {
            //Domingo
        }
        if (dia == Calendar.MONDAY) {
            //Lunes
        }
        if (dia == Calendar.TUESDAY) {
            //Martes
        }
        return true;
    }



    private boolean isSend(String status, String channel) {
        boolean seguir = false;
        switch (channel) {
            case "CRM":
                return false;
            case "CALLCENTER":
                if (status.equalsIgnoreCase("gxc")) {
                    seguir = true;
                } else {
                    seguir = false;
                }
                return seguir;
        }
        return true;
    }

}
