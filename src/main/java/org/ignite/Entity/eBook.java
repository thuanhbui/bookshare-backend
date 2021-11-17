package org.ignite.Entity;


import java.sql.Date;


public class eBook {

    private String title;
    private String description;
    private String imageLink;
    private String fileLink;
    private String language;
    private Date releaseYear;
    private Date lastUpdate;
    private int viewers;
    private int catalogId;

    public eBook(String title, String description, String imageLink, String fileLink,
                 String language, Date releaseYear, Date lastUpdate, int viewers, int catalogId) {
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
        this.fileLink = fileLink;
        this.language = language;
        this.releaseYear = releaseYear;
        this.lastUpdate = lastUpdate;
        this.viewers = viewers;
        this.catalogId = catalogId;
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

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public String toString() {
        return "eBook{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", fileLink='" + fileLink + '\'' +
                ", language='" + language + '\'' +
                ", releaseYear=" + releaseYear +
                ", lastUpdate=" + lastUpdate +
                ", viewers=" + viewers +
                ", catalogId=" + catalogId +
                '}';
    }
}
