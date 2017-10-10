/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hantsy
 */
public class UserData {
    List<User> users = new ArrayList<>();

    public UserData() {
    }
    
    public UserData(List<User> users) {
        this.users = users;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
   
}
