public class Catalog {
    private String nameCatalog;
    
    public String getCatalog() {
        return nameCatalog;
    }

    public void setCatalog(String  nameCatalog) {
        this.nameCatalog = nameCatalog;
    }

    @Override
    public String toString() {
        return "Catalog{" +
        "nameCatalog='" + nameCatalog + "}";
    }

}
