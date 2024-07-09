package com.prueba.service.impl;

import com.prueba.service.ConvertCadenService;
import com.prueba.service.CountCharacterService;
import com.prueba.service.DetailCaden;
import com.prueba.service.RepetitionCadenService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DetailCadenImpl implements DetailCaden {

  private final ConvertCadenService convertCadenService;
  private final CountCharacterService countCharacterService;
  private final RepetitionCadenService repetitionCadenService;

  @Override
  public Object summary(Map<String, String> maps) {
    Map<String, Object> objectMap = new HashMap<>();

    maps.entrySet().stream().forEach(s -> {
      Map<String, Object> objectMap2 = new HashMap<>();
      objectMap2.put("repetition word", repetitionText(s.getValue()));
      objectMap2.put("total character", countCharacter(s.getValue()));
      objectMap2.put("convert text", convertText(s.getValue()));
      objectMap.put(s.getKey(), objectMap2);
    });
    return objectMap;
  }

  private Map<String, Integer> repetitionText(String text) {
    Map<String, Integer> mapTempRepetition = repetitionCadenService.repetitionCadena(text);
    return mapTempRepetition;
  }

  private int countCharacter(String text) {
    return countCharacterService.totalCharacter(text);
  }

  private Map<String, Object> convertText(String text) {
    String result = "";
    //elimina todos los caracteres que no coincidadn con numero y letras
    String text2 = text.replaceAll("[^a-zA-Z0-9\\s]", "");
    Map<String, Object> convert = new HashMap<>();
    convert.put("Text Original", text);
    String upperCaseRegex = "^[A-Z0-9\\s]+$";
    String lowerCaseRegex = "^[a-z0-9\\s]+$";
    log.info("text2 {}", text2);
    if (text2.matches(upperCaseRegex)) {
      result = convertCadenService.toLowerCase(text);
      convert.put("Text LowerCase", result);
    } else {
      result = convertCadenService.toUpperCase(text);
      convert.put("Text UpperCase", result);
    }
    return convert;
  }
}
