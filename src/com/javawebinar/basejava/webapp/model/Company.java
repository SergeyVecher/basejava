package com.javawebinar.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private String companyName;
    private String companyAddress;
    private List<PeriodInCompany> periods;

    public Company(String companyName, String companyAddress, List<PeriodInCompany> periods) {
        Objects.requireNonNull(companyName);
        Objects.requireNonNull(companyAddress);
        Objects.requireNonNull(periods);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.periods = periods;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public List<PeriodInCompany> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getCompanyName(), company.getCompanyName()) &&
                Objects.equals(getCompanyAddress(), company.getCompanyAddress()) &&
                Objects.equals(getPeriods(), company.getPeriods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyName(), getCompanyAddress(), getPeriods());
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", periods=" + periods +
                '}';
    }
}
