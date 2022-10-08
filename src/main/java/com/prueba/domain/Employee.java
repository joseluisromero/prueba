package com.prueba.domain;

import com.prueba.domain.enums.GeneroType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identification;
    private String firstName;
    private String lastName;
    private String surnames;
    @Temporal(TemporalType.DATE)
    private Date birtDate;
    private Integer age;
    private Double stature;
    private LocalDateTime createDate;
    @Enumerated(EnumType.STRING)
    private GeneroType generoType;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Institution institution;
    private String statusCivil;
    private Date dateAcceptation;

    public String getNombresCompletos() {
        StringBuilder names = new StringBuilder();
        names.append(this.identification + ";");
        names.append(this.firstName + "");
        names.append(this.lastName);
        return names.toString();
    }
}
