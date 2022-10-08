package com.prueba.service.impl;

import com.prueba.service.LambdasService;
import com.prueba.service.dto.Person;
import com.prueba.service.dto.Travel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LambdasServiceImpl implements LambdasService {
    @Override
    public List<String> getTravelsForPersonNameMap() {
        List<Person> personList = buildPerson();
        List<String> personNames = personList.stream().map(Person::getName).collect(Collectors.toList());
        return personNames;
    }

    @Override
    public List<Person> getTravelsForPersonMap(String name) {
        List<Person> personList = buildPerson();
        List<Person> personNames = personList.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return personNames;
    }

    @Override
    public List<String> getTravels() {
        List<Person> personList = buildPerson();
        List<String> personNames = personList.stream()
                .map(Person::getTravels)
                .flatMap(List::stream)
                .map(Travel::getCountry).collect(Collectors.toList());
        return personNames;
    }

    @Override
    public List<String> getTravelsDistinct() {
        List<Person> personList = buildPerson();
        List<String> personNames = personList.stream()
                .map(Person::getTravels)
                .flatMap(List::stream)
                .map(Travel::getCountry)
                .distinct().collect(Collectors.toList());
        return personNames;
    }

    @Override
    public Long getTravelsDistinctCount() {
        List<Person> personList = buildPerson();
        Long count = personList.stream()
                .map(Person::getTravels)
                .flatMap(List::stream)
                .map(Travel::getCountry)
                .distinct().count();
        return count;
    }

    @Override
    public Map<String, Integer> getCountryForPerson() {
        List<Person> personList = buildPerson();
        Map<String,Integer>mapCountry=new HashMap<>();
        List<String> countriesDistinct=personList.stream()
                .map(Person::getTravels)
                .flatMap(List::stream)
                .map(Travel::getCountry)
                .distinct().collect(Collectors.toList());
        List<String> countries=personList.stream()
                .map(Person::getTravels)
                .flatMap(List::stream)
                .map(Travel::getCountry)
                .collect(Collectors.toList());

        countriesDistinct.stream().forEach(s -> {
           Long count= countries.stream().filter(s1->s1.equalsIgnoreCase(s)).count();
            mapCountry.put(s,Integer.parseInt(count.toString()));
        });
        return mapCountry;
    }

    private List<Person> buildPerson() {
        List<Person> pesons = new ArrayList<>();
        pesons.add(buildData("Jose Romero", List.of("Inglaterra", "Estados Unidos", "Francia")));
        pesons.add(buildData("David Bravo", List.of("Londres", "Argentina", "Colombia")));
        pesons.add(buildData("Mariela FLores", List.of("Inglaterra", "Colombia", "Ecuador", "Peru")));
        return pesons;
    }

    private Person buildData(String personParameter, List<String> countrys) {
        List<Travel> travelList = new ArrayList<>();
        Person person = new Person();
        person.setName(personParameter);
        countrys.stream().forEach(s -> {
            Travel travel = new Travel();
            travel.setCountry(s);
            travelList.add(travel);
        });
        person.setTravels(travelList);
        return person;
    }
}
