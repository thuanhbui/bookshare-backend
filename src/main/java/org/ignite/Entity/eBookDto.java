package org.ignite.Entity;

import java.sql.Date;
import java.time.Year;

public class eBookDto {
    private String bookId;
    private String title;
    private String description;
    private String imageLink;
    private String fileLink;
    private String language;
    private Date releaseYear;
    private Date lastUpdate;
    private Integer likes;
    private Integer userId;
    private Integer catalogId;
    private String catalogName;
    private String userName;

    public eBookDto() {

    }

    public eBookDto(eBookKey key, eBook value) {
        this.bookId = key.geteBookId();
        this.title = value.getTitle();
        this.description = value.getDescription();
        this.imageLink = "/FileUpload/img/" + value.getImageLink();
        this.fileLink = "/FileUpload/file/" + value.getFileLink();
        this.language = value.getLanguage();
        this.releaseYear = value.getReleaseYear();
        this.lastUpdate = value.getLastUpdate();
        this.likes = value.getLikes();
        this.catalogId = value.getCatalogId();
        this.userId = key.getUSERID();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
