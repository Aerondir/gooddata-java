package com.gooddata.md;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.codehaus.jackson.map.DeserializationConfig;


/**
 * Created Date: 22/04/2014
 */
public class AttributeServiceTest {

    @Test
    public void deserialize() throws Exception {
        final InputStream stream = getClass().getResourceAsStream("/md/attribute_example.json");
        final Attribute value = new ObjectMapper().readValue(stream, Attribute.class);
        assertThat(value, is(notNullValue()));
        final Attribute.Content content = value.getContent();
        assertNotNull(content);
    }
}
