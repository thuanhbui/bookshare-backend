package org.ignite.Entity;



public class eCatalogDto {
    private int catalogId;
    private String nameCatalog;
    private int adminId;
    public eCatalogDto() {

    }

    public eCatalogDto(eCatalogKey key, eCatalog value){
        this.catalogId = key.getCATALOGID();
        this.adminId = key.getADMINID();
        this.nameCatalog = value.getNameCatalog();
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getNameCatalog() {
        return nameCatalog;
    }

    public void setNameCatalog(String nameCatalog) {
        this.nameCatalog = nameCatalog;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
