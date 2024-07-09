package com.prueba.controller;


import com.prueba.service.DetailCaden;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/format")
@Slf4j
@RequiredArgsConstructor
public class FormatController {

  private final DetailCaden detailCaden;

  @PostMapping("/detalle-cadena")
  public ResponseEntity<Object> detailCaden(@RequestBody Map<String, String> maps) {
    log.info(maps.toString());
    return new ResponseEntity<>(detailCaden.summary(maps), HttpStatus.OK);
  }

}
