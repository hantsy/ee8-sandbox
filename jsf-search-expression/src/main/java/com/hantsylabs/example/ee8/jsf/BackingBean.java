/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsf;

import static java.lang.System.out;
import javax.enterprise.inject.Model;
import javax.faces.component.search.SearchExpressionContext;
import static javax.faces.component.search.SearchExpressionContext.createSearchExpressionContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Model
public class BackingBean {

    @Inject
    FacesContext context;

    public void foo() {

        SearchExpressionContext searchContext = createSearchExpressionContext(context, context.getViewRoot());

        context.getApplication()
                .getSearchExpressionHandler()
                .resolveComponent(
                        searchContext,
                        ":form:@parent",
                        (c, target) -> out.print(":form:@parent -> "+target.getId()));
        
        context.getApplication()
                .getSearchExpressionHandler()
                .resolveComponent(
                        searchContext,
                        ":form:@grandParent",
                        (c, target) -> out.print(":form:@grandParent -> "+target.getId()));
    }

}
