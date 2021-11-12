package org.ignite.Entity;



public class eCatalogDto {
    private int catalog_id;
    private String eBook_id;
    private String name_catalog;
    public eCatalogDto() {

    }

    public eCatalogDto(eCatalogKey key, eCatalog value){
        this.catalog_id = key.geteCatalogId();
        this.eBook_id = key.geteBookID();
        this.name_catalog = value.getNameeCatalog();
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public String geteBook_id() {
        return eBook_id;
    }

    public String getName_catalog() {
        return name_catalog;
    }

}
