/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.md;

import com.gooddata.AbstractService;
import com.gooddata.gdc.UriResponse;
import com.gooddata.project.Project;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 */
public class MetadataService extends AbstractService {

    public MetadataService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public <T extends Obj> T getObjByUri(String uri, Class<T> cls) {
        return restTemplate.getForObject(uri, cls);
    }

    public <T extends Obj> T getObjById(Project project, String id, Class<T> cls) {
        return restTemplate.getForObject(Obj.OBJ_URI, cls, project.getId(), id);
    }

    @SuppressWarnings("unchecked")
    public <T extends Obj> T createObj(Project project, T obj) {
        final UriResponse response = restTemplate.postForObject(Obj.URI, obj, UriResponse.class, project.getId());
        return getObjByUri(response.getUri(), (Class<T>) obj.getClass());
    }

    public Collection<Query.Entry> query(Project project, String type) {
        return restTemplate.getForObject(Query.URI, Query.class, project.getId(), type).getEntries();
    }
}
