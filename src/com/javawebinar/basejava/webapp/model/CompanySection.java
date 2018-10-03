package com.javawebinar.basejava.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        Objects.requireNonNull(companies);
        this.companies = companies;
    }

    public CompanySection(Company... companies) {
        this(Arrays.asList(companies));
    }


    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanySection)) return false;

        CompanySection that = (CompanySection) o;

        return getCompanies().equals(that.getCompanies());
    }

    @Override
    public int hashCode() {
        return getCompanies().hashCode();
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}
