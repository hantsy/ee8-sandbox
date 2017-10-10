/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import javax.faces.model.DataModel;
import javax.faces.model.FacesDataModel;

/**
 *
 * @author hantsy
 */
@FacesDataModel(forClass = UserData.class)
public class UserDataModel extends DataModel<User> {

    UserData data = null;
    int index = 0;

    public UserDataModel() {
        this(null);
    }

    public UserDataModel(UserData data) {
        this.data = data;
    }

    @Override
    public boolean isRowAvailable() {
        return this.index >= 0 && this.index < this.getRowCount();
    }

    @Override
    public int getRowCount() {
        return this.data != null ? this.data.getUsers().size() : 0;
    }

    @Override
    public User getRowData() {
        if (this.index >= 0 && this.index < this.getRowCount()) {
            return this.data.getUsers().get(this.index);
        }

        return null;
    }

    @Override
    public int getRowIndex() {
        return this.index;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.index = rowIndex;
    }

    @Override
    public Object getWrappedData() {
        return this.data;
    }

    @Override
    public void setWrappedData(Object data) {
        this.data = (UserData) data;
    }

}
