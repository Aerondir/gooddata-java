package com.gooddata.md;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Display form of attribute
 */
@JsonTypeName("attributeDisplayForm")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AttributeDisplayForm extends DisplayForm {

    @JsonCreator
    public AttributeDisplayForm(@JsonProperty("meta") Meta meta, @JsonProperty("content") Content content, @JsonProperty("links") Links links) {
        super(meta, content,links);
    }
}
