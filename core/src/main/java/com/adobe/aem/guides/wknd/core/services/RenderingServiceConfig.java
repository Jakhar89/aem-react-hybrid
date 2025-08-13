package com.adobe.aem.guides.wknd.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "React SSR Rendering Service Configuration",
    description = "Configuration for React Server-Side Rendering service"
)
public @interface RenderingServiceConfig {
    
    @AttributeDefinition(
        name = "SSR Endpoint URL",
        description = "URL of the Node.js SSR server endpoint",
        defaultValue = "http://localhost:4200/ssr"
    )
    String ssrEndpoint() default "http://localhost:4200/ssr";
    
    @AttributeDefinition(
        name = "Enable SSR",
        description = "Enable or disable server-side rendering",
        defaultValue = "true"
    )
    boolean enableSSR() default true;
}