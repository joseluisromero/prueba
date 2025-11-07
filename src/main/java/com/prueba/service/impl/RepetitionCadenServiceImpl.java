package com.prueba.service.impl;

import com.prueba.service.RepetitionCadenService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RepetitionCadenServiceImpl implements RepetitionCadenService {

  @Override
  public Map<String, Integer> repetitionCadena(String text) {
    String regex = "[^a-zA-Z0-9.@_-]";
    if (Objects.isNull(text) || text.isEmpty()) {
      return new HashMap<>();
    }
    //text = text.replaceAll(regex, "");
    String[] repetition = text.trim().split(" ");
    Map<String, Integer> maps = new HashMap<>();
    Map<String, Integer> mapsResult = new HashMap<>();
    Arrays.stream(repetition).toList().stream().forEach(palabra -> {
      palabra=palabra.replaceAll(regex, "");
      String finalPalabra = palabra;
      List<Entry<String, Integer>> find=mapsResult.entrySet().stream().filter(caden -> caden.getKey().equals(
          finalPalabra)).toList();
      if (find.isEmpty()) {
        mapsResult.put(palabra, 1);
      } else {
        mapsResult.replace(palabra,find.size()+1);
      }
    });
    return mapsResult;
  }

}
