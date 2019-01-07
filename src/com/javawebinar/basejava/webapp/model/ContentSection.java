package com.javawebinar.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContentSection extends Section {
    private static final long serialVersionUID = 1L;

    public ContentSection() {
    }

    private String content;

    public ContentSection(String content) {
        Objects.requireNonNull(content);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentSection)) return false;

        ContentSection that = (ContentSection) o;

        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        return getContent().hashCode();
    }
}
