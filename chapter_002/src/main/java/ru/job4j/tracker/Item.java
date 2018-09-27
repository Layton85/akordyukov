package ru.job4j.tracker;

import java.util.Arrays;

/**
 * Item - class holds information about some record.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Item {
    /** unique identification number of the item */
    private String id = null;
    /** name of the item */
    private String name = null;
    /** description of the item */
    private String desc = null;
    /** time of creating of the item*/
    private long created = 0;
    /** some comments */
    public String[] comments = null;

    /**
     * Constructor.
     * @param name - name of the item.
     * @param desc - description of the item.
     * @param created - time of creating of the item.
     */
    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * Constructor.
     * @param name - name of the item.
     * @param desc - description of the item.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * The method get id of this item
     * @return id of this item
     */
    public String getId() {
        return id;
    }

    /**
     * The method set id for this item
     * @param id - id for this item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The method get name of this item
     * @return name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * The method set name for this item
     * @param name - name for this item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method get description of this item
     * @return description of this item
     */
    public String getDesc() {
        return desc;
    }

    /**
     * The method set description for this item
     * @param desc - description for this item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * The method get time of creation of this item
     * @return time of creation of this item
     */
    public long getCreated() {
        return created;
    }

    /**
     * The method set time of creation for this item
     * @param created - time of creation for this item.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * The method get comments of this item
     * @return comments of this item
     */
    public String[] getComments() {
        return comments;
    }

    /**
     * The method sets comments for this item
     * @param comments - comments for this item.
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * Override method of Object method equals(Object obj)
     * @param other - another Item Object.
     * @return boolean value - result of comparison of this item with another one that received as a parameter.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other == this) {
            result = true;
        } else if (!(other instanceof Item) || (other == null)) {
            result = false;
        } else {
            Item that = (Item) other;
            result = this.id.equals(that.id)
                    && this.name.equals(that.name)
                    && this.desc.equals(that.desc)
                    && this.created == that.created
                    && Arrays.equals(this.comments, that.comments);
        }
        return result;
    }

    /**
     * Override method of Object method hashCode()
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int h = 0;
        h = prime * h + ((this.id == null) ? 0 : this.id.hashCode());
        h = prime * h + ((this.name == null) ? 0 : this.name.hashCode());
        h = prime * h + ((this.desc == null) ? 0 : this.desc.hashCode());
        h = prime * h + (int) this.created;
        for (int i = 0; i < this.comments.length; i++) {
            h = prime * h + ((this.comments[i] == null) ? 0 : this.comments[i].hashCode());
        }
        return h;
    }

    /**
     * Override method of Object method toString()
     * @return  returns a textual representation of an Item.
     */
    @Override
    public String toString() {
        String commentList = "";
        if (this.comments != null && this.comments.length > 0) {
            for (String str : this.comments) {
                commentList += str + System.lineSeparator();
            }
        }
        return "id: " + this.id + System.lineSeparator()
                + "name: " + this.name + System.lineSeparator()
                + "description: " + this.desc + System.lineSeparator()
                + "creation time: " + this.created + System.lineSeparator()
                + "comments: " + commentList + System.lineSeparator();
    }
}