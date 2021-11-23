package org.ignite.Entity;

import java.io.Serializable;

public class Like implements Serializable {

    private int liked;

    public Like(int like) {

        this.liked = like;
    }



    public int getLiked() {
        return liked;
    }

    public void setLiked(int like) {
        this.liked = like;
    }
}
