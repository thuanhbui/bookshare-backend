package model.Entity;

public class Book {

    private String title;
    private String description;
    private String image_link;
    private String file_link;
    private String language;
    private String release_year;
    private String  last_update;
    private int viewers;

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
        return image_link;
    }

    public void setImageLink(String imageLink) {
        this.image_link = imageLink;
    }

    public String getFileLink() {
        return file_link;
    }

    public void setFileLink(String fileLink) {
        this.file_link = fileLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseYear() {
        return release_year;
    }

    public void setReleaseYear(String releaseYear) {
        this.release_year = releaseYear;
    }

    public String getLastUpdate() {
        return last_update;
    }

    public void setLastUpdate(String lastUpdate) {
        this.last_update = lastUpdate;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers( int viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "Book{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", image_link='" + image_link + '\'' +
        ", file_link='" + file_link + '\'' +
        ", language='" + language + '\'' +
        ", release_year='" + release_year + '\'' +
        ", last_update='" + last_update + '\'' +
        ", viewers=" + viewers +"}";
    }

}