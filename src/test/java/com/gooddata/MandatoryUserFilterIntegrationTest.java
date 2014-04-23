package com.gooddata;

import com.gooddata.account.Account;
import com.gooddata.account.AccountService;
import com.gooddata.md.muf.Attribute;
import com.gooddata.md.muf.AttributeLinks;
import com.gooddata.md.muf.FilterService;
import com.gooddata.project.Project;
import com.gooddata.project.ProjectService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Properties;

public class MandatoryUserFilterIntegrationTest {

    public static void main(String[] args) throws IOException {
        final Properties prop = new Properties();
        prop.load(new FileInputStream("src/test/resources/config.properties"));
        final String login = prop.getProperty("user.login");
        final String pass = prop.getProperty("user.password");
        final GoodData gd = new GoodData(login, pass);

        final AccountService accountService = gd.getAccountService();
        final Account current = accountService.getCurrent();
        System.out.println(current.getId());

        final ProjectService projectService = gd.getProjectService();
        final Collection<Project> projects = projectService.getProjects();
        projects.forEach(p -> System.out.println("p.getId() = " + p.getId()));

        final Project project = gd.getProjectService().getProjectById("mwzb9jqs8admt0obea2t2jv8wqsbfl6v");
        System.out.println("project.getMeta().getTitle() = " + project.getMeta().getTitle());

        final FilterService filterService = gd.getFilterService();
        final Collection<AttributeLinks.AttributeLinkEntry> attributeList = filterService.getAttributeList(project.getId());
        final AttributeLinks.AttributeLinkEntry linkEntry = attributeList.stream().filter(el -> el.getTitle().equals("Provider Code")).findFirst().orElseThrow(NoSuchElementException::new);
        final Attribute attribute = filterService.getAttribute(linkEntry.getLink());
        final String attributeId = attribute.getId();

    }
}
