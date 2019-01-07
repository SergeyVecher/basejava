package com.javawebinar.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends Section {
    private static final long serialVersionUID = 1L;

    public CompanySection() {
    }

    private List<Company> companies;

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
