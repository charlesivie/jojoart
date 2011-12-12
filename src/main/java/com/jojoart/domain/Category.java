package com.jojoart.domain;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 07/12/2011
 * Time: 08:02
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Category implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean isActive;
    private boolean isDefaultCategory;
    @Null @OneToOne private Image image;

    public Category(String name, String description, boolean isActive, boolean isDefaultCategory, Image image) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isDefaultCategory = isDefaultCategory;
        this.image = image;
    }

    public Category(String name, String description, boolean isActive, boolean isDefaultCategory) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isDefaultCategory = isDefaultCategory;
    }

    public Category() {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isDefaultCategory() {
        return isDefaultCategory;
    }

    public void setDefaultCategory(boolean defaultCategory) {
        isDefaultCategory = defaultCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        return isActive == category.isActive
                && isDefaultCategory == category.isDefaultCategory
                && !(description != null ? !description.equals(category.description) : category.description != null)
                && !(image != null ? !image.equals(category.image) : category.image != null)
                && !(name != null ? !name.equals(category.name) : category.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isDefaultCategory ? 1 : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
