package org.ignite.Entity;

public class LikeDto {
    private Integer likeId;
    private Integer userId;
    private String eBookId;

    public LikeDto() {

    }

    public LikeDto(Integer key, Like value) {
        this.likeId = key;
        this.userId = value.getUserId();
        this.eBookId = value.geteBookId();
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String geteBookId() {
        return eBookId;
    }

    public void seteBookId(String eBookId) {
        this.eBookId = eBookId;
    }
}
