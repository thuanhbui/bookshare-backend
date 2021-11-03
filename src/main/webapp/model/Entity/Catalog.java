package model.Entity;

public class Catalog {
    private String name_catalog;

    public Catalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }
    
    public String getCatalog() {
        return name_catalog;
    }

    public void setCatalog(String  nameCatalog) {
        this.name_catalog = nameCatalog;
    }

    @Override
    public String toString() {
        return "Catalog{" +
        "nameCatalog='" + name_catalog + "\'}";
    }

}

