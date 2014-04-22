package com.gooddata.md;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created Date: 22/04/2014
 */
@JsonTypeName("attributeDisplayForm")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DisplayForm extends Obj {

    private Links links;

    public DisplayForm(@JsonProperty("meta") Meta meta, @JsonProperty("links") Links links) {
        super(meta);
        this.links = links;
    }

    public Links getLinks() {
            return links;
        }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private final String elements;

        @JsonCreator
        public Links(@JsonProperty("elements") String elements) {
            this.elements = elements;
        }

        public String getElements() {
            return elements;
        }
    }
}
