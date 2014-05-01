/*
 * Copyright (C) 2007-2014, GoodData(R) Corporation. All rights reserved.
 */
package com.gooddata.md;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Display form of attribute
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DisplayForm extends Obj {

    private final Content content;
    private Links links;

    @JsonCreator
    public DisplayForm(@JsonProperty("meta") Meta meta, @JsonProperty("content") Content content, @JsonProperty("links")Links links) {
        super(meta);
        this.content = content;
        this.links = links;
    }

    public Links getLinks() {
        return links;
    }

    public Content getContent() {
        return content;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Content {

        private final String formOf;
        private final String expression;
        private final String defaultValue;
        private final String ldmexpression;

        @JsonCreator
        public Content(@JsonProperty("formOf") String formOf, @JsonProperty("expression") String expression,
                       @JsonProperty("default") String defaultValue, @JsonProperty("ldmexpression") String ldmexpression) {
            this.formOf = formOf;
            this.expression = expression;
            this.defaultValue = defaultValue;
            this.ldmexpression = ldmexpression;
        }

        public String getFormOf() {
            return formOf;
        }

        public String getExpression() {
            return expression;
        }

        @JsonProperty("default")
        public String getDefaultValue() {
            return defaultValue;
        }

        public String getLdmexpression() {
            return ldmexpression;
        }
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
