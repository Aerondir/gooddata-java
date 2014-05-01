package com.gooddata.md.muf;

import com.gooddata.AbstractService;
import com.gooddata.md.Attribute;
import com.gooddata.md.Entry;
import com.gooddata.md.MetadataService;
import com.gooddata.project.Project;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created Date: 23/04/2014
 */
public class UserFilterService extends AbstractService {
    private final MetadataService metadataService;


    public UserFilterService(RestTemplate restTemplate, MetadataService metadataService) {
        super(restTemplate);
        this.metadataService = metadataService;
    }

    public Collection<Entry> getAttributeList(Project project) {
        return metadataService.find(project, Attribute.class);
    }

    public Attribute getAttribute(String link) {
        return restTemplate.getForObject(link, Attribute.class);
    }

    public java.util.List<AttributeElements.ElementLinkEntry> getElements(String uri) {
        return restTemplate.getForObject(uri, AttributeElements.class).getElements();
    }

    public UserFilter createFilter(String title,Project project, String attributeUri, String valueUri) {
        return metadataService.createObj(
                project,
                new UserFilter(
                        new UserFilter.FilterMeta(title, "userFilter"),
                        new UserFilter.Content("[" + attributeUri + "]=[" + valueUri + "]")
                )
        );
    }
}
