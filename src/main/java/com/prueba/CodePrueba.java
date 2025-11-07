package com.prueba;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CodePrueba {
    public static void main(String[] args) {
        int num = 50;
        int num2 = 50;
        num += 1;
        ++num2;

        System.out.println("mudulo" + num % 20);
        System.out.println("mudulo2 " + num2 % 20);
        Integer.valueOf("1");
        Integer.parseInt("1");
        String result = concatList(List.of("UNO", null));
        log.info(result);
    }

    private static String concatList(List<String> parameters) {
        return parameters.stream().filter(s -> !isBlank(s)).map(s -> new StringBuffer().append(s)).collect(Collectors.joining("_"));
    }

    public static boolean isBlank(Object value) {
        return Objects.isNull(value) || String.valueOf(value).trim().isEmpty();
    }
}
