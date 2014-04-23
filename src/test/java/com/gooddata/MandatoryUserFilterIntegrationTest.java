package com.gooddata;

import com.gooddata.account.Account;
import com.gooddata.account.AccountService;
import com.gooddata.md.MetadataService;
import com.gooddata.md.muf.Attribute;
import com.gooddata.md.muf.AttributeLinks;
import com.gooddata.md.muf.UserFilter;
import com.gooddata.md.muf.UserFilterService;
import com.gooddata.project.Project;
import com.gooddata.project.ProjectService;

import java.io.IOException;
import java.util.Collection;
import java.util.NoSuchElementException;

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

        final UserFilterService filterService = gd.getFilterService();
        final Collection<AttributeLinks.AttributeLinkEntry> attributeList = filterService.getAttributeList(project.getId());
        final AttributeLinks.AttributeLinkEntry linkEntry = attributeList.stream().filter(el -> el.getTitle().equals("Provider Code")).findFirst().orElseThrow(NoSuchElementException::new);
        final Attribute attribute = filterService.getAttribute(linkEntry.getLink());
        final String attributeUri = attribute.getMeta().getUri();
        System.out.println("attributeUri = " + attributeUri);
        final String elementsUri = attribute.getElementsUri();
        System.out.println("elementsUri = " + elementsUri);
        final String valueUri = filterService.getElements(elementsUri).stream().filter(el -> el.getTitle().equals("CQU")).map(el -> el.getUri()).findFirst().orElse("");
        System.out.println("valueUri = " + valueUri);

        final MetadataService metadataService = gd.getMetadataService();
        final UserFilter userFilter = filterService.createFilter("testFilter2", project, attributeUri, valueUri);
        System.out.println("userFilter = " + userFilter.getMeta().getUri());

        final UserFilter filter = metadataService.getObjByUri(userFilter.getMeta().getUri(), UserFilter.class);
        System.out.println("filter author = " + filter.getMeta().getAuthor());
    }
}
