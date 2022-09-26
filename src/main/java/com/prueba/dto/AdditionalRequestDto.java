package com.prueba.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
public class AdditionalRequestDto implements Serializable {

    private Long id;

    private AdditionalClientInfo client;

    private String additionalFirstName;

    private String additionalSecondName;

    private String additionalFirstSurname;

    private String additionalSecondSurname;

    private String additionalIdentification;

    private String additionalSex;

    private Timestamp additionalDateOfBirth;

    private String additionalCivilStatus;

    private String additionalRelationship;

    private String additionalPhoneNumber;

    private String additionalEmail;

    private int additionalLimit;

    private String additionalWrittenName;

    private Date createdTime;

    private boolean creationSent;

    private boolean notificationSent;

    public AdditionalRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdditionalClientInfo getClient() {
        return client;
    }

    public void setClient(AdditionalClientInfo client) {
        this.client = client;
    }

    public String getAdditionalFirstName() {
        return additionalFirstName;
    }

    public void setAdditionalFirstName(String additionalFirstName) {
        this.additionalFirstName = additionalFirstName;
    }

    public String getAdditionalSecondName() {
        return additionalSecondName;
    }

    public void setAdditionalSecondName(String additionalSecondName) {
        this.additionalSecondName = additionalSecondName;
    }

    public String getAdditionalFirstSurname() {
        return additionalFirstSurname;
    }

    public void setAdditionalFirstSurname(String additionalFirstSurname) {
        this.additionalFirstSurname = additionalFirstSurname;
    }

    public String getAdditionalSecondSurname() {
        return additionalSecondSurname;
    }

    public void setAdditionalSecondSurname(String additionalSecondSurname) {
        this.additionalSecondSurname = additionalSecondSurname;
    }

    public String getAdditionalIdentification() {
        return additionalIdentification;
    }

    public void setAdditionalIdentification(String additionalIdentification) {
        this.additionalIdentification = additionalIdentification;
    }

    public String getAdditionalSex() {
        return additionalSex;
    }

    public void setAdditionalSex(String additionalSex) {
        this.additionalSex = additionalSex;
    }

    public Timestamp getAdditionalDateOfBirth() {
        return additionalDateOfBirth;
    }

    public void setAdditionalDateOfBirth(Timestamp additionalDateOfBirth) {
        this.additionalDateOfBirth = additionalDateOfBirth;
    }

    public String getAdditionalCivilStatus() {
        return additionalCivilStatus;
    }

    public void setAdditionalCivilStatus(String additionalCivilStatus) {
        this.additionalCivilStatus = additionalCivilStatus;
    }

    public String getAdditionalRelationship() {
        return additionalRelationship;
    }

    public void setAdditionalRelationship(String additionalRelationship) {
        this.additionalRelationship = additionalRelationship;
    }

    public String getAdditionalPhoneNumber() {
        return additionalPhoneNumber;
    }

    public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
        this.additionalPhoneNumber = additionalPhoneNumber;
    }

    public String getAdditionalEmail() {
        return additionalEmail;
    }

    public void setAdditionalEmail(String additionalEmail) {
        this.additionalEmail = additionalEmail;
    }

    public int getAdditionalLimit() {
        return additionalLimit;
    }

    public void setAdditionalLimit(int additionalLimit) {
        this.additionalLimit = additionalLimit;
    }

    public String getAdditionalWrittenName() {
        return additionalWrittenName;
    }

    public void setAdditionalWrittenName(String additionalWrittenName) {
        this.additionalWrittenName = additionalWrittenName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isCreationSent() {
        return creationSent;
    }

    public void setCreationSent(boolean creationSent) {
        this.creationSent = creationSent;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }

    public void setNotificationSent(boolean notificationSent) {
        this.notificationSent = notificationSent;
    }
}
