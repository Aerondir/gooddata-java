package com.gooddata.md;

import com.gooddata.md.muf.Attribute;
import com.gooddata.md.muf.AttributeLinks;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created Date: 22/04/2014
 */
public class AttributeServiceTest {

    @Test
    public void testAttributeMapping() throws Exception {
        final InputStream stream = getClass().getResourceAsStream("/md/attribute_example.json");
        final Attribute value = new ObjectMapper().readValue(stream, Attribute.class);
        assertNotNull(value);
        final Meta meta = value.getMeta();
        assertNotNull(meta);
        assertEquals("Provider Code", meta.getTitle());
        final Attribute.Content content = value.getContent();
        assertNotNull(content);
        final Collection<Attribute.DisplayForm> displayForms = content.getDisplayForms();
        assertNotNull(displayForms);
        assertEquals(1, displayForms.size());
        final String elements = ((List<Attribute.DisplayForm>) displayForms).get(0).getLinks().getElements();
        assertEquals("/gdc/md/{project-id}/obj/50/elements", elements);
    }

    @Test
    public void testAttributeLinksMapping() throws Exception {
        final InputStream stream = getClass().getResourceAsStream("/md/attribute_list_example.json");
        final AttributeLinks value = new ObjectMapper().readValue(stream, AttributeLinks.class);
        assertNotNull(value);

        final List<AttributeLinks.AttributeLinkEntry> entries = (List<AttributeLinks.AttributeLinkEntry>) value.getEntries();
        assertEquals(2, entries.size());
        assertEquals("Quarter (Enrolment Date)", entries.get(1).getTitle());
        assertEquals("/gdc/md/mwzb9jqs8admt0obea2t2jv8wqsbfl6v/obj/271", entries.get(0).getLink());
    }
}
