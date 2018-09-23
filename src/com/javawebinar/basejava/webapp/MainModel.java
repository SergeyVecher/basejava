package com.javawebinar.basejava.webapp;

import com.javawebinar.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;

public class MainModel {
    public static void main(String[] args) {
        int count = 0;
        Resume resume = new Resume("fullName");
        resume.addContact(ContactType.EMAIL, "email");
        resume.addContact(ContactType.PHONE, "phone number");
        resume.addContact(ContactType.GITHUB, "profile GitHub");

        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(new ContentSection("Achievement one"),
                new ContentSection("Achievement two"))));

        resume.addSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(new Company("University one",
                        "address", LocalDate.of(2003, 9, 1),
                        LocalDate.of(2009, 7, 1), "student"),
                new Company("University two",
                        "address", LocalDate.of(2003, 9, 1),
                        LocalDate.of(2009, 7, 1), "student"))));

        for (SectionType type : SectionType.values()) {
            if (resume.getSection(type) != null) continue;
            count++;
            resume.addSection(type, new ContentSection("random " + count));
        }

        for (ContactType type : ContactType.values()) {
            System.out.println(resume.getContact(type));
        }

        for (SectionType type : SectionType.values()) {
            System.out.println(resume.getSection(type).toString());
        }
    }
}
