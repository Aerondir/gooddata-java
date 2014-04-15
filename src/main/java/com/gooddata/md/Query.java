package com.gooddata.md;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Collection;

/**
 * Metadata query
 */
@JsonTypeName("query")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Query {

    public static final String URI = "/gdc/md/{projectId}/query/{type}";

    private final Collection<Entry> entries;

    private final Meta meta;

    @JsonCreator
    public Query(@JsonProperty("entries") Collection<Entry> entries, @JsonProperty("meta") Meta meta) {
        this.entries = entries;
        this.meta = meta;
    }

    public Collection<Entry> getEntries() {
        return entries;
    }

    public Meta getMeta() {
        return meta;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Entry {

        private final String link;
        private final String title;
        private final String summary;
        private final String category;
        private final String author;
        private final String contributor;
        private final String deprecated;
        private final String identifier;
        private final String locked;
        private final String tags;
        private final String created; // todo date time
        private final String updated; // todo date time

        @JsonCreator
        public Entry(@JsonProperty("link") String link, @JsonProperty("title") String title,
                     @JsonProperty("summary") String summary, @JsonProperty("category") String category,
                     @JsonProperty("author") String author, @JsonProperty("contributor") String contributor,
                     @JsonProperty("deprecated") String deprecated, @JsonProperty("identifier") String identifier,
                     @JsonProperty("locked") String locked, @JsonProperty("tags") String tags,
                     @JsonProperty("created") String created, @JsonProperty("updated") String updated) {
            this.link = link;
            this.title = title;
            this.summary = summary;
            this.category = category;
            this.author = author;
            this.contributor = contributor;
            this.deprecated = deprecated;
            this.identifier = identifier;
            this.locked = locked;
            this.tags = tags;
            this.created = created;
            this.updated = updated;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }

        public String getSummary() {
            return summary;
        }

        public String getCategory() {
            return category;
        }

        public String getAuthor() {
            return author;
        }

        public String getContributor() {
            return contributor;
        }

        public String getDeprecated() {
            return deprecated;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getLocked() {
            return locked;
        }

        public String getTags() {
            return tags;
        }

        public String getCreated() {
            return created;
        }

        public String getUpdated() {
            return updated;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public static class Meta {
        private final String category;
        private final String summary;
        private final String title;

        @JsonCreator
        public Meta(@JsonProperty("category") String category, @JsonProperty("summary") String summary,
                    @JsonProperty("title") String title) {
            this.category = category;
            this.summary = summary;
            this.title = title;
        }

        public String getCategory() {
            return category;
        }

        public String getSummary() {
            return summary;
        }

        public String getTitle() {
            return title;
        }
    }

}
