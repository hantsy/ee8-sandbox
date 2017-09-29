/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import java.util.Arrays;

/**
 *
 * @author hantsy
 */
public class Fixtures {

    public static Post newPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setTags(Arrays.asList("test", "test 2"));
        return post;
    }

//    public static Comment newComment(String content) {
//        return Comment.builder().content(content).build();
//    }
}
