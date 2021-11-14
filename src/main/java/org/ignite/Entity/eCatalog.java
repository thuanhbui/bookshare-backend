package org.ignite.Entity;

public class eCatalog {
    private String nameCatalog;

    public eCatalog() {

    }

    public String getNameCatalog() {
        return nameCatalog;
    }

    public void setNameCatalog(String nameCatalog) {
        this.nameCatalog = nameCatalog;
    }

    @Override
    public String toString() {
        return "eCatalog{" +
                "nameCatalog='" + nameCatalog + '\'' +
                '}';
    }
}
