package com.multiverse.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component (service = Servlet.class,
   property = {
        "sling.servlet.method=GET",
        "sling.servlet.paths=/bin/pagemanagerapi"
   })
public class CreatePageServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CreatePageServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        LOG.info("MyCustomServlet was hit!");

        ResourceResolver resourceResolver = request.getResourceResolver();

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        if (pageManager == null) {
            response.getWriter().write("PageManager not available.");
            return;
        }

        String parentPath = "/content/we-retail/language-masters/en";
        String newPageName = "program-page";
        String newPageTitle = "Program Page";
        String templatePath = "/conf/we-retail/settings/wcm/templates/hero-page";
        try {
            Page newPage = pageManager.create(parentPath, newPageName, templatePath, newPageTitle);
            response.getWriter().write("Page created successfully: " + newPage.getTitle());
        } catch (WCMException e) {
            throw new RuntimeException(e);
        }


        // Optional: Add additional logic here (e.g., accessing resources, processing data)
    }
}

