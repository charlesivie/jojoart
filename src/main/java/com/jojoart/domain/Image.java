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
public class Image implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int imageSizeType;
    private String mimeType;
    private byte[] imageBlob;
    private String name;
    private String description;
    private boolean isActive;
    private boolean isDefaultImage;

    @ManyToOne private ImageGroup imageGroup;

    public Image(
            int imageSizeType,
            String mimeType,
            byte[] imageBlob,
            String name,
            String description,
            boolean active,
            boolean defaultImage,
            ImageGroup imageGroup) {

        this.imageSizeType = imageSizeType;
        this.mimeType = mimeType;
        this.imageBlob = imageBlob;
        this.name = name;
        this.description = description;
        isActive = active;
        isDefaultImage = defaultImage;
        this.imageGroup = imageGroup;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageSizeType() {
        return imageSizeType;
    }

    public void setImageSizeType(int imageSizeType) {
        this.imageSizeType = imageSizeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDefaultImage() {
        return isDefaultImage;
    }

    public void setDefaultImage(boolean defaultImage) {
        isDefaultImage = defaultImage;
    }

    public ImageGroup getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(ImageGroup imageGroup) {
        this.imageGroup = imageGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        return imageSizeType == image.imageSizeType
                && isActive == image.isActive
                && isDefaultImage == image.isDefaultImage
                && !(description != null ? !description.equals(image.description) : image.description != null)
                && !(imageGroup != null ? !imageGroup.equals(image.imageGroup) : image.imageGroup != null)
                && Arrays.equals(imageBlob, image.imageBlob)
                && !(mimeType != null ? !mimeType.equals(image.mimeType) : image.mimeType != null)
                && !(name != null ? !name.equals(image.name) : image.name != null);

    }

    @Override
    public int hashCode() {
        int result = imageSizeType;
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (imageBlob != null ? Arrays.hashCode(imageBlob) : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isDefaultImage ? 1 : 0);
        result = 31 * result + (imageGroup != null ? imageGroup.hashCode() : 0);
        return result;
    }
}
