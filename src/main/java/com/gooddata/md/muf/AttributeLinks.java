package com.gooddata.md.muf;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created Date: 23/04/2014
 */
@JsonTypeName("query")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AttributeLinks {

    public static final String URI = "/gdc/md/{projectId}/query/attributes";

    private Collection<AttributeLinkEntry> entries = new ArrayList<>();


    @JsonCreator
    public AttributeLinks( @JsonProperty("entries") Collection<AttributeLinkEntry> entries) {
        this.entries = entries;
    }

    public Collection<AttributeLinkEntry> getEntries() {
        return entries;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class AttributeLinkEntry  {
        @JsonProperty("title")
        String title;
        @JsonProperty("link")
        String link;

        @JsonCreator
        public AttributeLinkEntry(@JsonProperty("title") String title, @JsonProperty("link") String link) {
            this.title = title;
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }
    }
}
