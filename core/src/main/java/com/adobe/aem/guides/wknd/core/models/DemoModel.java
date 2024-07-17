package com.adobe.aem.guides.wknd.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.GsonBuilder;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * Account Details Component
 */
@Model(
    adaptables = {SlingHttpServletRequest.class, Resource.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
    resourceType = "wknd/components/accordion-react"
)
@Exporter(name = "jackson", extensions = "json")
public class DemoModel {

    @ValueMapValue
    private String slingText;

    @ValueMapValue
    private String slingTitle;

    @ValueMapValue
    private Boolean slingCheck;


    @JsonIgnore
    private String json;

    @PostConstruct
    protected void init() {
        this.json = new GsonBuilder().create().toJson(this);
    }

    public String getSlingText() { 
        return this.slingText; 
    }
    public String getSlingTitle() { return this.slingTitle; }
    public Boolean getSlingCheck() { return this.slingCheck; }

  

    public String getJson() { return json; }
}
