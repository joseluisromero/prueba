package com.prueba.service;

import com.prueba.service.dto.Person;
import com.prueba.service.dto.Travel;

import java.util.List;
import java.util.Map;

public interface LambdasService {
    List<String> getTravelsForPersonNameMap();
    List<Person> getTravelsForPersonMap(String name);
    List<String> getTravels();
    List<String> getTravelsDistinct();
    Long getTravelsDistinctCount();
    Map<String ,Integer> getCountryForPerson();
}
