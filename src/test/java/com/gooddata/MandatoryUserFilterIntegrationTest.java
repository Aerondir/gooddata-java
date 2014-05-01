package com.gooddata;

import com.gooddata.account.Account;
import com.gooddata.account.AccountService;
import com.gooddata.md.Attribute;
import com.gooddata.md.DisplayForm;
import com.gooddata.md.MetadataService;
import com.gooddata.md.Restriction;
import com.gooddata.md.muf.UserFilter;
import com.gooddata.md.muf.UserFilterService;
import com.gooddata.project.Project;
import com.gooddata.project.ProjectService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MandatoryUserFilterIntegrationTest {

    public static void main(String[] args) throws IOException {
        final String login = "maxim.razumov@open.edu.au";
        final String pass = "test123";
        final GoodData gd = new GoodData(login, pass);

        final AccountService accountService = gd.getAccountService();
        final Account current = accountService.getCurrent();
        System.out.println(current.getId());

        final ProjectService projectService = gd.getProjectService();
        final Collection<Project> projects = projectService.getProjects();
        projects.forEach(p -> System.out.println("p.getId() = " + p.getId()));

        final Project project = gd.getProjectService().getProjectById("mwzb9jqs8admt0obea2t2jv8wqsbfl6v");
        System.out.println("project.getMeta().getTitle() = " + project.getMeta().getTitle());

        final MetadataService metadataService = gd.getMetadataService();

        final UserFilterService filterService = gd.getFilterService();
        final String attributeUri = metadataService.getObjUri(project, Attribute.class, Restriction.title("Provider Code"));
        System.out.println("attributeUri = " + attributeUri);

        final Attribute attribute = metadataService.getObjByUri(attributeUri, Attribute.class);
        final List<DisplayForm> displayForms = new ArrayList<>(attribute.getContent().getDisplayForms());
        final String elementsUri = displayForms.get(0).getLinks().getElements();
        System.out.println("elementsUri = " + elementsUri);

        final String valueUri = filterService.getElements(elementsUri).stream().filter(el -> el.getTitle().equals("CQU")).map(el -> el.getUri()).findFirst().orElse("");
        System.out.println("valueUri = " + valueUri);

        final UserFilter userFilter = filterService.createFilter("testFilter2", project, attributeUri, valueUri);
        System.out.println("userFilter = " + userFilter.getMeta().getUri());

        final UserFilter filter = metadataService.getObjByUri(userFilter.getMeta().getUri(), UserFilter.class);
        System.out.println("filter author = " + filter.getMeta().getAuthor());
    }
}
