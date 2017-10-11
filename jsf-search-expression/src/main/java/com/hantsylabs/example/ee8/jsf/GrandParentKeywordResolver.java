/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import javax.faces.component.UIComponent;
import javax.faces.component.search.SearchExpressionContext;
import javax.faces.component.search.SearchKeywordContext;
import javax.faces.component.search.SearchKeywordResolver;

/**
 *
 * @author hantsy
 */
public class GrandParentKeywordResolver extends SearchKeywordResolver {

    @Override
    public boolean isResolverForKeyword(SearchExpressionContext searchExpressionContext, String keyword) {
        return "grandParent".equals(keyword);
    }

    @Override
    public void resolve(SearchKeywordContext searchKeywordContext, UIComponent current, String keyword) {
        UIComponent parent = current.getParent();
        if (parent != null) {
            searchKeywordContext.invokeContextCallback(parent.getParent());
        } else {
            searchKeywordContext.setKeywordResolved(true);
        }
    }
}
