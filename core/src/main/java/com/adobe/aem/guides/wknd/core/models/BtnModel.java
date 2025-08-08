package com.adobe.aem.guides.wknd.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.GsonBuilder;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import com.google.gson.annotations.Expose;

import com.adobe.aem.guides.wknd.core.services.RenderingService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(
    adaptables = {SlingHttpServletRequest.class, Resource.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
@Getter
public class BtnModel {
    private static final Logger LOG = LoggerFactory.getLogger(BtnModel.class);

    @Expose
    private String resourceType;
    @Expose
    private String resourcePath;

    @ValueMapValue
    @Expose
    private String btnText;

    @JsonIgnore
    private String ssrHtml;

    @JsonIgnore
    private String json;

    @OSGiService(injectionStrategy = InjectionStrategy.OPTIONAL)
    private RenderingService reactSsrService;

    @JsonIgnore
    @SlingObject
    private Resource currentResource;

    @PostConstruct
    protected void init() {
        try {
            this.resourceType = currentResource.getResourceType();
            this.resourcePath = currentResource.getPath();

            LOG.info("Initializing BtnModel with resourceType: {}, resourcePath: {}", this.resourceType, this.resourcePath);
            this.json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this);
            
            if (reactSsrService != null) {
                LOG.info("RenderingService is available, calling render with JSON: {}", this.json);
                this.ssrHtml = reactSsrService.render(this.json);
                LOG.info("SSR HTML generated: {}", this.ssrHtml);
            } else {
                LOG.warn("RenderingService OSGi service not available");
                this.ssrHtml = "<!-- SSR service not available -->";
            }
        } catch (Exception e) {
            LOG.error("Error initializing BtnModel: {}", e.getMessage(), e);
            this.ssrHtml = "<!-- Error in SSR initialization: " + e.getMessage() + " -->";
            // Don't re-throw the exception to prevent model instantiation failure
        }
    }
}