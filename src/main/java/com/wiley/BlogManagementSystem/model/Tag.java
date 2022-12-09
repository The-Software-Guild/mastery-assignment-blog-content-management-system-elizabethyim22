package com.wiley.BlogManagementSystem.model;

import java.util.Objects;

public class Tag {
    private int tag_id;
    private String tag_name;

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name(){
        return tag_name;
    }

    public void setTag_name(String tag_name){
        this.tag_name=tag_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return getTag_id() == tag.getTag_id() && getTag_name().equals(tag.getTag_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTag_id(), getTag_name());
    }
}

