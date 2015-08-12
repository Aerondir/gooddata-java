package com.gooddata.util;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class GDDateClass {
    private LocalDate date;

    @JsonCreator
    public GDDateClass(@JsonProperty("date") @JsonDeserialize(using = GDDateDeserializer.class) final LocalDate date) {
        this.date = date;
    }

    @JsonSerialize(using = GDDateSerializer.class)
    public LocalDate getDate() {
        return date;
    }

}
