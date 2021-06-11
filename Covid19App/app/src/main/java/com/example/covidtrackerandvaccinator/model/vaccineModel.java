package com.example.covidtrackerandvaccinator.model;

public class vaccineModel {

 private String vaccineCenter;
 private String vaccineCharges;
 private String vaccineAge;
 private String vaccineTimings;
 private String vaccineName;
 private String vaccineCenterTime;
 private String vaccineCenterAddress;
 private String vaccineAvailable;


    public vaccineModel(String vaccineCenter, String vaccineCharges, String vaccineAge, String vaccineTimings, String vaccineName, String vaccineCenterTime, String vaccineCenterAddress, String vaccineAvailable) {
        this.vaccineCenter = vaccineCenter;
        this.vaccineCharges = vaccineCharges;
        this.vaccineAge = vaccineAge;
        this.vaccineTimings = vaccineTimings;
        this.vaccineName = vaccineName;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccineCenterAddress = vaccineCenterAddress;
        this.vaccineAvailable = vaccineAvailable;
    }

    public vaccineModel() {

    }


    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccineCharges() {
        return vaccineCharges;
    }

    public void setVaccineCharges(String vaccineCharges) {
        this.vaccineCharges = vaccineCharges;
    }

    public String getVaccineAge() {
        return vaccineAge;
    }

    public void setVaccineAge(String vaccineAge) {
        this.vaccineAge = vaccineAge;
    }

    public String getVaccineTimings() {
        return vaccineTimings;
    }

    public void setVaccineTimings(String vaccineTimings) {
        this.vaccineTimings = vaccineTimings;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public String getVaccineCenterAddress() {
        return vaccineCenterAddress;
    }

    public void setVaccineCenterAddress(String vaccineCenterAddress) {
        this.vaccineCenterAddress = vaccineCenterAddress;
    }

    public String getVaccineAvailable() {
        return vaccineAvailable;
    }

    public void setVaccineAvailable(String vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
    }
}
