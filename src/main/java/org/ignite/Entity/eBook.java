package org.ignite.Entity;


import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


public class eBook {

    private String title;
    private String description;
    private MultipartFile imgMulti;
    private String imageLink;
    private MultipartFile fileMulti;
    private String fileLink;
    private String language;
    private Date releaseYear;
    private Date lastUpdate;
    private Integer likes;
    private Integer catalogId;

    public eBook() {}

    public eBook(String title, String description, String imageLink, String fileLink,
                 String language, Date releaseYear, Date lastUpdate, Integer likes, Integer catalogId) {
        this.title = title;
        this.description = description;
        this.imageLink = imageLink;
        this.fileLink = fileLink;
        this.language = language;
        this.releaseYear = releaseYear;
        this.lastUpdate = lastUpdate;
        this.likes = likes;
        this.catalogId = catalogId;
    }

    public eBook(String title, String description, MultipartFile imgMulti, String imageLink, MultipartFile fileMulti, String fileLink,
                 String language, Date releaseYear, Date lastUpdate, int likes, int catalogId) {
        this.title = title;
        this.description = description;
        this.imgMulti = imgMulti;
        this.imageLink = imageLink;
        this.fileMulti = fileMulti;
        this.fileLink = fileLink;
        this.language = language;
        this.releaseYear = releaseYear;
        this.lastUpdate = lastUpdate;
        this.likes = likes;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public MultipartFile getImgMulti() {
        return imgMulti;
    }

    public void setImgMulti(MultipartFile imgMulti) {
        this.imgMulti = imgMulti;
    }

    public MultipartFile getFileMulti() {
        return fileMulti;
    }

    public void setFileMulti(MultipartFile fileMulti) {
        this.fileMulti = fileMulti;
    }

    @Override
    public String toString() {
        return "eBook{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgMulti=" + imgMulti +
                ", imageLink='" + imageLink + '\'' +
                ", fileMulti=" + fileMulti +
                ", fileLink='" + fileLink + '\'' +
                ", language='" + language + '\'' +
                ", releaseYear=" + releaseYear +
                ", lastUpdate=" + lastUpdate +
                ", likes=" + likes +
                ", catalogId=" + catalogId +
                '}';
    }
}
