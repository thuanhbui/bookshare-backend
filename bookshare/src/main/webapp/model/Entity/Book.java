public class Book {

    private String title;
    private String description;
    private String imageLink;
    private String fileLink;
    private String language;
    private String releaseYear;
    private String  lastUpdate;
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

    public void setViewers( int viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "Book{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", imageLink='" + imageLink + '\'' +
        ", fileLink='" + fileLink + '\'' +
        ", language='" + language + '\'' +
        ", releaseYear='" + releaseYear + '\'' +
        ", lastUpdate='" + lastUpdate + '\'' +
        ", viewers=" + viewers +"}";
    }

}
