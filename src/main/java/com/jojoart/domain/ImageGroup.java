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
public class ImageGroup implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private boolean isActive;
    private boolean isDefaultGroup;

    @ManyToOne private Category category;

    public ImageGroup(String name, String description, boolean isActive, boolean isDefaultGroup, Category category) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isDefaultGroup = isDefaultGroup;
        this.category = category;
    }

    public ImageGroup() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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
        isActive = active;
    }

    public boolean isDefaultGroup() {
        return isDefaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        isDefaultGroup = defaultGroup;
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
        if (!(o instanceof ImageGroup)) return false;

        ImageGroup imageGroup = (ImageGroup) o;

        return  isActive == imageGroup.isActive
                && isDefaultGroup == imageGroup.isDefaultGroup
                && !(description != null ? !description.equals(imageGroup.description) : imageGroup.description != null)
                && !(category != null ? !category.equals(imageGroup.category) : imageGroup.category != null)
                && !(name != null ? !name.equals(imageGroup.name) : imageGroup.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (isDefaultGroup ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }


}
