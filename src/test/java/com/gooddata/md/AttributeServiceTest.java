package com.gooddata.md;

import com.gooddata.md.muf.Attribute;
import com.gooddata.md.muf.AttributeElements;
import com.gooddata.md.muf.AttributeLinks;
import com.gooddata.md.muf.UserFilter;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.InputStream;
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
        assertEquals("49", value.getId());
        final Attribute.Content content = value.getContent();
        assertNotNull(content);
        final List<Attribute.DisplayForm> displayForms = content.getDisplayForms();
        assertNotNull(displayForms);
        assertEquals(1, displayForms.size());
        assertEquals("/gdc/md/{project-id}/obj/50/elements", value.getElementsUri());
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

    @Test
    public void testFilterMapping() throws Exception {
        final InputStream stream = getClass().getResourceAsStream("/md/user_filter_example.json");
        final UserFilter value = new ObjectMapper().readValue(stream, UserFilter.class);
        assertNotNull(value);

    }

    @Test
    public void testAttributeElementsMapping() throws Exception {
        final InputStream stream = getClass().getResourceAsStream("/md/attribute_elems_example.json");
        final AttributeElements value = new ObjectMapper().readValue(stream, AttributeElements.class);
        assertNotNull(value);
        assertNotNull(value.getElements());
        final AttributeElements.ElementLinkEntry entry = value.getElements().get(0);
        assertEquals("CQU", entry.getTitle());
        assertEquals("3531", entry.getId());

    }

}
