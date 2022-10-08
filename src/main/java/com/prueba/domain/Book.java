package com.prueba.domain;

import lombok.*;


public class Book {
    private Integer id;
    private String name;
    private String description;
    private Integer yearPublication;

    public Book() {
    }

    public Book(Integer id, String name, String description, Integer yearPublication) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearPublication = yearPublication;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(Integer yearPublication) {
        this.yearPublication = yearPublication;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", yearPublication=" + yearPublication +
                '}';
    }
}
