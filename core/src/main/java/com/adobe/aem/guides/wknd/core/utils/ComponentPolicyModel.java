/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2020 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.aem.guides.wknd.core.utils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class ComponentPolicyModel {

    private static final String POLICY_PATH = "/conf/wknd/settings/wcm/policies/wknd/components/button-react";

    @Inject
    private ResourceResolverFactory resolverFactory;

    private String cssClasses;

    @PostConstruct
    protected void init() {
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(null)) {
            Resource policyResource = resolver.getResource(POLICY_PATH);
            if (policyResource != null) {
                cssClasses = policyResource.getValueMap().get("cssClasses", String.class);
            }
        } catch (Exception e) {
            // Handle exception (e.g., logging, fallback values)
            cssClasses = "";
        }
    }

    public String getCssClasses() {
        return cssClasses;
    }
}