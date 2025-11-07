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

  @PostMapping("/variable-entorno")
  public ResponseEntity<Object> getVariableEntorno(@RequestBody String var) {
    log.info(var.toString());
    String[] maps = var.trim().split("\n");
    String vecVar[] = new String[maps.length];
    String vecSecret[] = new String[maps.length];
    int sizeVarOk = 0;
    int sizeVarSecret = 0;
    boolean secret = false;
    //Recorriendo el vector que tienen toda las  variables  y secretos
    for (int i = 0; i < maps.length; i++) {
      if (i + 1 != maps.length) {
        if (maps[i + 1].contains("location:") || secret) {
          secret = true;
          vecSecret[i] = maps[i];
          sizeVarSecret++;
        } else {
          vecVar[i] = maps[i];
          sizeVarOk++;
        }
      } else {
        if (secret) {
          vecSecret[i] = maps[i];
          sizeVarSecret++;
        } else {
          vecVar[i] = maps[i];
          sizeVarOk++;
        }
      }
    }
    log.info(vecVar.toString());
    log.info(vecSecret.toString());
    //creando los vectores  con el tamaño adecuado
    String vecVarOk[] = new String[sizeVarOk];
    String vecSecretOk[] = new String[sizeVarSecret];
    //Para solo pasar el numero total de valores  no  nulos
    int m = 0;
    for (int i = 0; i < vecVar.length; i++) {
      if (vecVar[i] != null) {
        vecVarOk[m] = vecVar[i];
        m++;
      }

    }
    //Para solo pasar el numero total de valores  no  nulos
    int s = 0;
    for (int i = 0; i < vecSecret.length; i++) {
      if (vecSecret[i] != null) {
        vecSecretOk[s] = vecSecret[i];
        s++;
      }

    }
    String variable = "";
    StringBuilder str = new StringBuilder();
    //Para sacar las  variables de entornos
    for (int i = 0; i < vecVarOk.length; i++) {

      String cadena = "";
      if (i % 2 == 0) {
        variable = "";
        cadena = buildText("name:", String.valueOf(vecVarOk[i]));
        variable = cadena + "=";

      } else {

        cadena = buildText("value:", String.valueOf(vecVarOk[i]));
        cadena = variable + cadena + ";";
        str.append(cadena);
        str.append("\n");
      }
    }
    //Para sacar los secretos solo el valor
    for (int i = 0; i < vecSecretOk.length; i++) {
      String cadena = "";
      if (i % 2 == 0) {
        variable = "";
        cadena = buildText("name:", String.valueOf(vecSecretOk[i]));
        variable = cadena + "=";
      } else {
        cadena = buildText("location:", "");
        cadena = variable + cadena + ";";
        str.append(cadena);
        str.append("\n");
      }
    }
    log.info("Variables de entornos", str);
    return new ResponseEntity<>(str, HttpStatus.OK);
  }

  //Para quitar los  valores  innecsario de  name y value asi como location
  private String buildText(String regex, String text) {
    String text2 = "";
    if (text.trim().contains(regex)) {
      text2 = text.replace(regex, "");
      text2 = text2.replace("\"", "");
    }
    return text2.trim();
  }

}
