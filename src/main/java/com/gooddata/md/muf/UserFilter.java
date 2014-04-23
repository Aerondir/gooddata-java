package com.gooddata.md.muf;

import com.gooddata.md.Meta;
import com.gooddata.md.Obj;
import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created Date: 23/04/2014
 */
@JsonTypeName("userFilter")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserFilter extends Obj {

    private final Content content;

    @JsonCreator
    public UserFilter(@JsonProperty("meta") FilterMeta meta, @JsonProperty("content") Content content) {
        super(meta);
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Content {
        private final String expression;


        @JsonCreator
        public Content(@JsonProperty("expression") String expression) {
            this.expression = expression;
        }

        public String getExpression() {
            return expression;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class FilterMeta extends Meta {
        public FilterMeta(@JsonProperty("title") String title, @JsonProperty("category") String category) {
            super(title, category);
        }
    }
}
