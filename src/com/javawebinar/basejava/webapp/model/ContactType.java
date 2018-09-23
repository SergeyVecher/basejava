package com.javawebinar.basejava.webapp.model;

import java.util.Objects;

public enum ContactType {
    PHONE("Mobile phone"),
    EMAIL("Email"),
    GITHUB("Profile GitHub");

    private final String title;

    ContactType(String title) {
        Objects.requireNonNull(title);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + ": ";
    }
}
