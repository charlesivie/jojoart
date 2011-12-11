package com.jojoart.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 08:02
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class ImageVersion implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private byte[] imageBlob;
    private int width;
    
    @ManyToOne private Image image;

    public ImageVersion(byte[] imageBlob, int width, Image image) {
        this.imageBlob = imageBlob;
        this.width = width;
        this.image = image;
    }

    public ImageVersion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageVersion)) return false;

        ImageVersion that = (ImageVersion) o;

        return width == that.width
                && Arrays.equals(imageBlob, that.imageBlob)
                && !(image != null ? !image.equals(that.image) : that.image != null);

    }

    @Override
    public int hashCode() {
        int result = imageBlob != null ? Arrays.hashCode(imageBlob) : 0;
        result = 31 * result + width;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
