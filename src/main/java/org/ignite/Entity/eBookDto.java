package org.ignite.Entity;

public class eBookDto {
    private String bookId;
    private String title;
    private String description;
    private String image_link;
    private String file_link;
    private String language;
    private String release_year;
    private String last_update;
    private int viewers;
    private int userId;

    public eBookDto() {

    }

    public eBookDto(eBookKey key, eBook value) {
        this.bookId = key.geteBookID();
        this.title = value.getTitle();
        this.description = value.getDescription();
        this.image_link = value.getImageLink();
        this.file_link = value.getFileLink();
        this.language = value.getLanguage();
        this.release_year = value.getReleaseYear();
        this.last_update = value.getLastUpdate();
        this.viewers = value.getViewers();
        this.userId = key.getUserID();
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getFile_link() {
        return file_link;
    }

    public String getLanguage() {
        return language;
    }

    public String getRelease_year() {
        return release_year;
    }

    public String getLast_update() {
        return last_update;
    }

    public int getViewers() {
        return viewers;
    }

    public int getUserId() {
        return userId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public void setFile_link(String file_link) {
        this.file_link = file_link;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
