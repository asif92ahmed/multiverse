package com.multiverse.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyNewModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    private String sampleField;

    public String getTitle(){
        return title;
    }

    public  String getDescription(){
        return description;
    }

    @PostConstruct
    protected void init() {

        sampleField = "Hi from Model";
    }
    public  String getSampleField(){

        return sampleField;
    }
}
