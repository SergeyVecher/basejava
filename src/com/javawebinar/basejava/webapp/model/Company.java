package com.javawebinar.basejava.webapp.model;

import com.javawebinar.basejava.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.javawebinar.basejava.webapp.util.DateUtil.NOW;
import static com.javawebinar.basejava.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    public Company() {
    }

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

    public Company(String companyName, String companyAddress, PeriodInCompany... periodInCompanies) {
        this(companyName, companyAddress, Arrays.asList(periodInCompanies));
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PeriodInCompany implements Serializable {
        private static final long serialVersionUID = 1L;

        public PeriodInCompany() {
        }

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startTime;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finishTime;
        private String description;

        public PeriodInCompany(LocalDate startTime, String description) {
            this.startTime = startTime;
            this.finishTime = NOW;
            this.description = description;
        }

        public PeriodInCompany(int startYear, Month startMonth, int finishYear, Month finishMonth, String description) {
            Objects.requireNonNull(startMonth);
            Objects.requireNonNull(finishMonth);
            Objects.requireNonNull(description);
            this.startTime = of(startYear, startMonth);
            this.finishTime = of(finishYear, finishMonth);
            this.description = description;
        }

        public LocalDate getStartTime() {
            return startTime;
        }

        public LocalDate getFinishTime() {
            return finishTime;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PeriodInCompany)) return false;
            PeriodInCompany periodInCompany = (PeriodInCompany) o;
            return Objects.equals(getStartTime(), periodInCompany.getStartTime()) &&
                    Objects.equals(getFinishTime(), periodInCompany.getFinishTime()) &&
                    Objects.equals(getDescription(), periodInCompany.getDescription());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getStartTime(), getFinishTime(), getDescription());
        }

        @Override
        public String toString() {
            return "PeriodInCompany{" +
                    "startTime=" + startTime +
                    ", finishTime=" + finishTime +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
