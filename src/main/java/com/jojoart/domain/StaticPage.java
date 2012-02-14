package com.jojoart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;

import static java.net.URLEncoder.encode;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 12/02/2012
 * Time: 20:28
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class StaticPage {

    @Id
    private String path;
    private String name;
    private String htmlContent;
    private boolean active;

    public StaticPage(String name, String htmlContent, boolean active) {
        setPath(name);
        this.name = name;
        this.htmlContent = htmlContent;
        this.active = active;
    }

    public StaticPage() {}

    public StaticPage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        try {
            this.path = encode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaticPage)) return false;

        StaticPage that = (StaticPage) o;

        return active == that.active
                && !(htmlContent != null ? !htmlContent.equals(that.htmlContent) : that.htmlContent != null)
                && !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (htmlContent != null ? htmlContent.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
