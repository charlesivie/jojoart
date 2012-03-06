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
    @Transient
    private byte[] imageBlob;
    private String imageType;
    
    @ManyToOne private Image image;

    public ImageVersion(byte[] imageBlob, String imageType, Image image) {
        this.imageBlob = imageBlob;
        this.imageType = imageType;
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

    public void setImageType(String imageType) {
        this.imageType = imageType;
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

        return !(image != null ? !image.equals(that.image) : that.image != null)
                && Arrays.equals(imageBlob, that.imageBlob)
                && !(imageType != null ? !imageType.equals(that.imageType) : that.imageType != null);

    }

    @Override
    public int hashCode() {
        int result = imageBlob != null ? Arrays.hashCode(imageBlob) : 0;
        result = 31 * result + (imageType != null ? imageType.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
