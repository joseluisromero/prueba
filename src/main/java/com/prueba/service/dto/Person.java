package com.prueba.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Person implements Serializable {
    private String name;
    @Builder.Default
    private List<Travel> travels=new ArrayList<>();
}
