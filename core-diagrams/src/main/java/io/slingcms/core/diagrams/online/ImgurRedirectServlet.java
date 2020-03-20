/**
 * $Id: ProxyServlet.java,v 1.4 2013/12/13 13:18:11 david Exp $
 * Copyright (c) 2011-2012, JGraph Ltd
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

import static io.slingcms.core.diagrams.Contants.DOMAIN_NAME;

/**
 * Servlet implementation ProxyServlet
 */
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/diagrams/i",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET })
@SuppressWarnings("serial")
public class ImgurRedirectServlet extends SlingAllMethodsServlet
{

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImgurRedirectServlet()
	{
		super();
	}

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		int last = uri.lastIndexOf("/");

		if (last > 0)
		{
			String id = uri.substring(last + 1);
			response.setHeader("Location",
                    "https://" + DOMAIN_NAME + "/?chrome=0&lightbox=1&layers=1&url=http%3A%2F%2Fi.imgur.com%2F"
							+ id + ".png"
							+ "&edit=https%3A%2F%2F" + DOMAIN_NAME + "%2F%3Furl%3Dhttp%253A%252F%252Fi.imgur.com%252F"
							+ id + ".png");
			response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
