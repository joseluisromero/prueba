package com.prueba.service.impl;

import com.prueba.service.ConvertCadenService;
import com.prueba.service.CountCharacterService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConvertCadenServiceImpl implements ConvertCadenService {


  @Override
  public String toLowerCase(String text) {
    if (Objects.isNull(text) || text.isEmpty()) {
      return "";
    }
    return text.trim().toLowerCase();
  }

  @Override
  public String toUpperCase(String text) {
    if (Objects.isNull(text) || text.isEmpty()) {
      return "";
    }
    return text.trim().toUpperCase();
  }
}
