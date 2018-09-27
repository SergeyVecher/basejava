package com.javawebinar.basejava.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.javawebinar.basejava.webapp.util.DateUtil.of;

public class PeriodInCompany {
    private final LocalDate startTime;
    private final LocalDate finishTime;
    private final String description;

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
