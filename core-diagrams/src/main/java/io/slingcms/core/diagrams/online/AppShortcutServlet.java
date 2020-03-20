/**
 * $Id: ErrorServlet.java,v 1.6 2014/02/21 12:01:30 gaudenz Exp $
 * Copyright (c) 2014, JGraph Ltd
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class OpenServlet
 */
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/diagrams/proxy",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET})
public class AppShortcutServlet extends SlingAllMethodsServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppShortcutServlet()
	{
		super();
	}


	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
	{
		response.setHeader("Location", "index.html?offline=1");
		response.setStatus(HttpServletResponse.SC_FOUND);
	}

}
