package com.javawebinar.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Company {
    private String companyName;
    private String companyAddress;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String description;

    public Company(String companyName, String companyAddress, LocalDate startDate, LocalDate finishDate, String description) {
        Objects.requireNonNull(companyName);
        Objects.requireNonNull(companyAddress);
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(finishDate);
        Objects.requireNonNull(description);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", description='" + description + '\'' +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (!getCompanyName().equals(company.getCompanyName())) return false;
        if (!getCompanyAddress().equals(company.getCompanyAddress())) return false;
        if (!getStartDate().equals(company.getStartDate())) return false;
        if (!getFinishDate().equals(company.getFinishDate())) return false;
        return getDescription().equals(company.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getCompanyName().hashCode();
        result = 31 * result + getCompanyAddress().hashCode();
        result = 31 * result + getStartDate().hashCode();
        result = 31 * result + getFinishDate().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
