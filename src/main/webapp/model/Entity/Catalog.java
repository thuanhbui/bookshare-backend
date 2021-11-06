package model.Entity;

public class Catalog {
    private String name_catalog;

    public Catalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }

    public String getNameCatalog() {
        return name_catalog;
    }

    public void setNameCatalog(String nameCatalog) {
        this.name_catalog = nameCatalog;
    }

    @Override
    public String toString() {
        return "Catalog{" + "name_catalog='" + name_catalog + "\'}";
    }

}
