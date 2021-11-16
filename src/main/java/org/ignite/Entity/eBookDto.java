package org.ignite.Entity;

public class eBookDto {
    private String bookId;
    private String title;
    private String description;
    private String imageLink;
    private String fileLink;
    private String language;
    private String releaseYear;
    private String lastUpdate;
    private int viewers;
    private int userId;
    private int catalogId;

    public eBookDto() {

    }

    public eBookDto(eBookKey key, eBook value) {
        this.bookId = key.geteBookId();
        this.title = value.getTitle();
        this.description = value.getDescription();
        this.imageLink = value.getImageLink();
        this.fileLink = value.getFileLink();
        this.language = value.getLanguage();
        this.releaseYear = value.getReleaseYear();
        this.lastUpdate = value.getLastUpdate();
        this.viewers = value.getViewers();
        this.userId = key.getCATALOGID();
        this.catalogId = key.getCATALOGID();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
}
