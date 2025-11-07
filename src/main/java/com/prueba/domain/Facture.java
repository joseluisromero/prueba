package com.prueba.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "facture")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer factureId;
    String name;
    Date createBy=new Date();
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "facture_id",referencedColumnName ="factureId" )
    List<Detail> details = new ArrayList<>();

}
