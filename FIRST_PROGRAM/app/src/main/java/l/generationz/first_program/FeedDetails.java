package l.generationz.first_program;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Tanya on 25.12.2017.
 */

public class FeedDetails {

    private Long date;
    private String name;
    private String image;
    private int likes;
    private Bitmap binaryImage;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Bitmap getBinaryImage() {
        return binaryImage;
    }

    public void setBinaryImage(Bitmap binaryImage) {
        this.binaryImage = binaryImage;
    }
}
