package com.javawebinar.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends Section {
    private static final long serialVersionUID = 1L;

    public ListSection() {
    }

    private List<Section> sections;

    public ListSection(List<Section> sections) {
        Objects.requireNonNull(sections);
        this.sections = sections;
    }

    public ListSection(Section... sections) {
        this(Arrays.asList(sections));
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;

        ListSection that = (ListSection) o;

        return getSections().equals(that.getSections());
    }

    @Override
    public int hashCode() {
        return getSections().hashCode();
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "sections=" + sections +
                '}';
    }
}
