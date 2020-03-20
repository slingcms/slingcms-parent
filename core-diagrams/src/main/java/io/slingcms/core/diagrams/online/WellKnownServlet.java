/**
 * Copyright (c) 2020, JGraph Ltd
 * Copyright (c) 2020, draw.io AG
 */
package io.slingcms.core.diagrams.online;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Servlet to fake a .well-known directory
 */
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/diagrams/wellknown",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET })
@SuppressWarnings("serial")
public class WellKnownServlet extends SlingAllMethodsServlet
{
	private static final Logger log = Logger
			.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WellKnownServlet()
	{
		super();
	}


	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
	{
        // GAE can't serve dot prefixed folders
        String uri = request.getRequestURI().replace("/.", "/");

        if (uri.toLowerCase().contains(".json"))
        {
        	response.setContentType("application/json");
        }

        // Serve whatever was requested from .well-known
        try (InputStream in = getServletContext().getResourceAsStream(uri))
        {
            if (in == null)
            {
            	response.sendError(404);
                return;
            }
            
            byte[] buffer = new byte[8192];
            int count;

            while ((count = in.read(buffer)) > 0)
            {
            	response.getOutputStream().write(buffer, 0, count);
            }
            
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}
