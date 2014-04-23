package com.gooddata.md;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created Date: 22/04/2014
 */
@JsonTypeName("attribute")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Attribute extends Obj {

    private final Content content;

    @JsonCreator
    public Attribute(@JsonProperty("meta") Meta meta, @JsonProperty("content") Content content) {
        super(meta);
        this.content = content;
    }


    public Content getContent() {
        return content;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Content {

        Collection<DisplayForm> displayForms = new ArrayList<>();

        @JsonCreator
        public Content(@JsonProperty("displayForms") Collection<DisplayForm> displayForms) {
            this.displayForms = displayForms;
        }

        public Collection<DisplayForm> getDisplayForms() {
            return displayForms;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class DisplayForm {

        private Links links;

        @JsonCreator
        public DisplayForm(@JsonProperty("links") Links links) {
            this.links = links;
        }

        public Links getLinks() {
            return links;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
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
}
