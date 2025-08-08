package com.adobe.aem.guides.wknd.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = RenderingService.class)
public class RenderingService {
    private static final Logger LOG = LoggerFactory.getLogger(RenderingService.class);
    
    private static final String NODE_SSR_ENDPOINT = "http://localhost:4200/ssr"; // Fixed endpoint URL
    
    private final ObjectMapper mapper = new ObjectMapper();

    public String render(String jsonData) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(NODE_SSR_ENDPOINT);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(jsonData, StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    Map<String, Object> respMap = mapper.readValue(response.getEntity().getContent(), Map.class);
                    // Expecting JSON response like {"html": "<rendered html>"}
                    Object html = respMap.get("html");
                    if (html != null) {
                        return html.toString();
                    } else {
                        LOG.error("No 'html' key in SSR response");
                        return "<!-- SSR response missing html -->";
                    }
                } else {
                    LOG.error("SSR server responded with status code: {}", statusCode);
                    return "<!-- SSR server error: " + statusCode + " -->";
                }
            }
        } catch (IOException e) {
            LOG.error("Error calling SSR server", e);
            return "<!-- SSR call failed: " + e.getMessage() + " -->";
        }
    }
}