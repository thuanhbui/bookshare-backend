package org.ignite.Entity;

public class eCatalog {
    private String name_catalog;

    public eCatalog() {

    }

    public eCatalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }


    public String getNameeCatalog() {
        return name_catalog;
    }

    public void setNameeCatalog(String nameCatalog) {
        this.name_catalog = nameCatalog;
    }


    @Override
    public String toString() {
        return "eCatalog{" +
                "name_catalog='" + name_catalog + '\'' +
                '}';
    }
}
