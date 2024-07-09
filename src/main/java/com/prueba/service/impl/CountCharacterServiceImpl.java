package com.prueba.service.impl;

import com.prueba.service.CountCharacterService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountCharacterServiceImpl implements CountCharacterService {

  @Override
  public int totalCharacter(String text) {
    if (Objects.isNull(text) || text.isEmpty()) {
      return 0;
    }
    text = text.replace(" ", "");
    return text.trim().length();
  }
}
