package com.huxian.model;

import java.io.Serializable;

/**
 * @author huxian99
 */
public class Actor implements Serializable {
    private static final long serialVersionUID = -337822238788581692L;

    private String id;
    private String name;
    private ImageLink avatars;
    private String alt;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatars(ImageLink avatars) {
        this.avatars = avatars;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public ImageLink getAvatars() {
        return avatars;
    }

    public String getAlt() {
        return alt;
    }
}
