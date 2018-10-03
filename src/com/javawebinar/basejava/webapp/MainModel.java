package com.javawebinar.basejava.webapp;

import com.javawebinar.basejava.webapp.model.*;

import java.time.Month;
import java.util.Arrays;

public class MainModel {
    static {
        int count = 0;
        Resume resume = new Resume("fullName");
        resume.addContact(ContactType.EMAIL, "email");
        resume.addContact(ContactType.PHONE, "phone number");
        resume.addContact(ContactType.GITHUB, "profile GitHub");

        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(new ContentSection("Achievement one"),
                new ContentSection("Achievement two"))));

        resume.addSection(SectionType.EDUCATION, new CompanySection(Arrays.asList(new Company("University one",
                        "address", Arrays.asList(new Company.PeriodInCompany(2003, Month.MAY, 2008, Month.MAY, "student"),
                new Company.PeriodInCompany(2003, Month.MAY, 2008, Month.MAY, "student"))),
                new Company("University two",
                        "address", Arrays.asList(new Company.PeriodInCompany(2011, Month.MAY, 2014, Month.MAY, "student"),
                        new Company.PeriodInCompany(2011, Month.MAY, 2014, Month.MAY, "student"))))));

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
