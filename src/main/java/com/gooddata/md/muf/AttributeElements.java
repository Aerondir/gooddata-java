package com.gooddata.md.muf;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.util.UriTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created Date: 23/04/2014
 */
@JsonTypeName("attributeElements")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AttributeElements {

    private List<ElementLinkEntry> elements = new ArrayList<>();

    @JsonCreator
    public AttributeElements(@JsonProperty("elements") List<ElementLinkEntry> elements) {
        this.elements = elements;
    }

    public List<ElementLinkEntry> getElements() {
        return elements;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class ElementLinkEntry {

        private static final String URI = "/gdc/md/{projectId}/obj/{objId}/elements?id={elementId}";
        private static final UriTemplate TEMPLATE = new UriTemplate(URI);
        @JsonProperty("title")
        String title;
        @JsonProperty("uri")
        String uri;
        
        public String getId() {
                return TEMPLATE.match(getUri()).get("elementId");
            }
        @JsonCreator
        public ElementLinkEntry(@JsonProperty("title") String title, @JsonProperty("uri") String uri) {
            this.title = title;
            this.uri = uri;
        }

        public String getTitle() {
            return title;
        }

        public String getUri() {
            return uri;
        }
    }

}
