package com.prueba.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detail")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer detailId;
    String productName;
    Double value;
    int unit;
    Double total;
    Date createBy=new Date();
    @ManyToOne(fetch = FetchType.LAZY)
    //para el nombre que estara la foreingKey
    @JoinColumn(name = "facture_id" )
    Facture facture;
}
