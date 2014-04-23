package com.gooddata.md.muf;

import com.gooddata.AbstractService;
import org.springframework.web.client.RestTemplate;

/**
 * Created Date: 23/04/2014
 */
public class FilterService extends AbstractService {

    public FilterService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public AttributeLinks getAttributeList(String projectId) {
        final AttributeLinks attributes = restTemplate.getForObject(AttributeLinks.URI, AttributeLinks.class, projectId);
        return attributes;
    }
}
