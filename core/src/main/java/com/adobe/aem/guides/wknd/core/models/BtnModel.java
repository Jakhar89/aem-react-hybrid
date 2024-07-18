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
    resourceType = "wknd/components/button-react"
)
@Exporter(name = "jackson", extensions = "json")
public class BtnModel {

    @ValueMapValue
    private String btnText;

    @ValueMapValue
    private String btnType;


    @JsonIgnore
    private String json;

    @PostConstruct
    protected void init() {
        this.json = new GsonBuilder().create().toJson(this);
    }

    public String getBtnText() { return this.btnText; }
    public String getBtnType() { return this.btnType; }

    public String getJson() { return json; }
}
