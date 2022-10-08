package com.prueba;

import com.prueba.domain.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EjemploStream {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "FISICA", "Descripcion para  fisica uni ", 2000));
        books.add(new Book(2, "QUIMICA", "Descripcion para QUIMICA cole", 2010));
        books.add(new Book(3, "LENGUAJE", "Descripcion para Lenguaje uni", 1980));
        books.add(new Book(4, "Matematica", "Descripcion para Matematica cole", 2015));
        books.add(new Book(5, "Dibujo", "Descripcion para Dibujo escuela", 2006));

        System.out.println("Filter con java 8 +");
        books.stream().filter(book -> book.getYearPublication() > 2000).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("Filter con java 8 + mas ordenamiento");
        books.stream().filter(book -> book.getYearPublication() > 2000).sorted((o1, o2) -> o1.getYearPublication().compareTo(o2.getYearPublication())).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("Java Stream Sorted (Comparator y comparing)");
        books.stream().sorted(Comparator.comparing(Book::getYearPublication)).forEach(System.out::println);
        System.out.println("Java Stream Sorted (Comparator y thenComparing)");
        Comparator<Book> comparadorMultiple = Comparator.comparing(Book::getName).thenComparing(Comparator.comparing(Book::getYearPublication));
        books.stream().sorted(comparadorMultiple).forEach(System.out::println);

        System.out.println("Filter concatenados con java 8 +");
        books.stream().filter(book -> book.getYearPublication() > 2000).filter(book2 -> book2.getDescription().contains("uni")).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("Filter condicional return nueva lista con java 8 +");
        List<Book> books_dos = books.stream().filter(book -> book.getYearPublication() > 2000 && book.getDescription().contains("cole")).collect(Collectors.toList());
        books_dos.forEach(System.out::println);

        System.out.println("Java Stream Map nos permite realizar una transformación rápida de los datos");
        books.stream().map(Book::getYearPublication).reduce((integer, integer2) -> integer + integer2).ifPresent(System.out::println);
        System.out.println("MapInt y MapDouble contiene otros  metodos(sum,average,min,max,summary statistics)");
        Integer total = books.stream().mapToInt(Book::getYearPublication).sum();
        Double promedio = books.stream().mapToInt(Book::getYearPublication).average().getAsDouble();
        Integer min = books.stream().mapToInt(Book::getYearPublication).min().getAsInt();
        Integer max = books.stream().mapToInt(Book::getYearPublication).max().getAsInt();
        System.out.println("total: " + total);
        System.out.println("promedio: " + promedio);
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println();
        //otra forma del reduce con inicializador de valor
        Integer total2=books.stream().map(Book::getYearPublication).reduce(0,Integer::sum);
        System.out.println("total2 : " + total2);


    }
}
