package org.ignite.Entity;



public class eCatalogDto {
    private int catalog_id;
    private int admin_id;
    private String name_catalog;
    public eCatalogDto() {

    }

    public eCatalogDto(eCatalogKey key, eCatalog value){
        this.catalog_id = key.getCATALOG_ID();
        this.admin_id = key.getADMIN_ID();
        this.name_catalog = value.getNameCatalog();
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getName_catalog() {
        return name_catalog;
    }

    public void setName_catalog(String name_catalog) {
        this.name_catalog = name_catalog;
    }
}
