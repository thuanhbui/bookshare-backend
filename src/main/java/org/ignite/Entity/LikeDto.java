package org.ignite.Entity;

public class LikeDto {
    private Integer userId;
    private String eBookId;
    private int like;

    public LikeDto() {

    }

    public LikeDto(LikeKey key, Like value) {
        this.userId = key.getUserId();
        this.eBookId = key.getEBOOKID();
        this.like = value.getLiked();
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
