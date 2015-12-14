package com.huxian.model;

import java.io.Serializable;

/**
 * @author huxian99
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 5815225776282331501L;

    private String text;
    private int icon;

    public Menu(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }
}
