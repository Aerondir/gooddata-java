package com.gooddata.md.muf;

import com.gooddata.md.Meta;
import com.gooddata.md.Obj;
import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

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

    public String getId() {
        return OBJ_TEMPLATE.match(getMeta().getUri()).get("objId");
    }

    public String getElementsUri() {
        return getContent().getDisplayForms().get(0).getLinks().getElements();
    }

    public Content getContent() {
        return content;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Content {

        List<DisplayForm> displayForms = new ArrayList<>();

        @JsonCreator
        public Content(@JsonProperty("displayForms") List<DisplayForm> displayForms) {
            this.displayForms = displayForms;
        }

        public List<DisplayForm> getDisplayForms() {
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
