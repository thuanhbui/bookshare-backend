package org.ignite.Entity;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;

public class LikeKey implements Serializable {
    private Integer userId;
    @AffinityKeyMapped
    private String EBOOKID;

    public LikeKey() {

    }

    public LikeKey(Integer userId, String eBookId) {
        this.userId = userId;
        this.EBOOKID = eBookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEBOOKID() {
        return EBOOKID;
    }

    public void setEBOOKID(String EBOOKID) {
        this.EBOOKID = EBOOKID;
    }
}
