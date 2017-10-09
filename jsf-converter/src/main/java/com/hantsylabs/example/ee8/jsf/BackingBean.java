/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hantsy
 */
@RequestScoped
@Named
public class BackingBean {

    @Inject
    @FacesConverter(value="tagsConverter", managed = true)
    TagsConverter mytagsConverter;

    List<String> tags = new ArrayList<>();
    List<String> mytags = new ArrayList<>();

    public TagsConverter getMytagsConverter() {
        return mytagsConverter;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getMytags() {
        return mytags;
    }

    public void setMytags(List<String> mytags) {
        this.mytags = mytags;
    }

}
