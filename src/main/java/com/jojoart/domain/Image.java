package com.jojoart.domain;

import javax.persistence.*;
import java.io.Serializable;

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
    private String name;
    private String description;
    private String mimeType;
    private boolean isActive;

    @OneToOne private Category category;


    public Image(String name, String description, String mimeType, boolean active, Category category) {
        this.name = name;
        this.description = description;
        this.mimeType = mimeType;
        this.isActive = active;
        this.category = category;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        return isActive == image.isActive &&
                !(category != null ? !category.equals(image.category) : image.category != null)
                && !(description != null ? !description.equals(image.description) : image.description != null)
                && !(mimeType != null ? !mimeType.equals(image.mimeType) : image.mimeType != null)
                && !(name != null ? !name.equals(image.name) : image.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
