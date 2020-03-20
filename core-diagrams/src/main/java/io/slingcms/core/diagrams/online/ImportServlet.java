package io.slingcms.core.diagrams.online;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/diagrams/import",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST })
@SuppressWarnings("serial")
public class ImportServlet extends SlingAllMethodsServlet
{

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
	{
		if(!request.getRemoteHost().endsWith("atlassian.net"))
		{
			
		} 
		else 
		{
			System.out.println(request);
		}
	}

	
}
