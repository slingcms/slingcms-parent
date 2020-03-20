package io.slingcms.core.diagrams.online;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple client-side logging servlet
 */
@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/diagrams/log",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.methods=" + HttpConstants.METHOD_GET })
public class LogServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2360583959079622105L;
	
	private static final Logger log = Logger.getLogger(LogServlet.class
			.getName());
	
	static byte[] singleByteGif = { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61, 0x1, 0x0, 0x1, 0x0, (byte) 0x80, 0x0, 0x0, (byte)  0xff, (byte)  0xff,  (byte) 0xff, 0x0, 0x0, 0x0, 0x2c, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0, 0x0, 0x2, 0x2, 0x44, 0x1, 0x0, 0x3b };

	/**
	 * The start string of error message we want to ignore
	 */
	static String[] configArray;
	
	static Set<String> ignoreFilters;

	/**
	 * The start string of error message we want to reduce to a warning
	 */
	static String[] warningArray;
		
	static Set<String> warningFilters;

	static
	{
		configArray = new String[]
	    {
	    	"Uncaught TypeError: frames.ezLinkPreviewIFRAME.postMessage is not a function", // third party Chrome plugin
	    	"Uncaught TypeError: Cannot set property 'tgt' of null" // Tampermonkey extension, https://github.com/kogg/InstantLogoSearch/issues/199
	    };
		
		ignoreFilters = new HashSet<String>(Arrays.asList(configArray));
		
		warningArray = new String[]
	    {
	    	"Uncaught Error: Client got in an error state. Call reset() to reuse it!", // dropbox auth failing
	    	"Error: Client got in an error state. Call reset() to reuse it!"
	    };
		
		warningFilters = new HashSet<String>(Arrays.asList(warningArray));
	}

    @Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
	{
		doPost(request, response);
	}

    @Override
	public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
	{
		try
		{
			String message = request.getParameter("msg");
			String severity = request.getParameter("severity");
			String version = request.getParameter("v");
			String stack = request.getParameter("stack");
			
			String userAgent = request.getHeader("User-Agent");

			if (message != null)
			{
				Level severityLevel = Level.CONFIG;
				
				if (severity != null)
				{
					severityLevel = Level.parse(severity);
				}
				
				if (severityLevel.intValue() >= 1000)
				{
					// Tidy up severes
					message = message == null ? message : URLDecoder.decode(message, "UTF-8");
					version = version == null ? version : URLDecoder.decode(version, "UTF-8");
					stack = stack == null ? stack : URLDecoder.decode(stack, "UTF-8");
					
					severityLevel = filterClientErrors(message, userAgent);
				}

				message = version != null ? message + "\nVERSION=" + version : message;		
				message = stack != null ? message + "\n" + stack : message;

				log.log(severityLevel, "CLIENT-LOG:" + message);
			}

			response.setContentType("image/gif");
			OutputStream out = response.getOutputStream();
			out.write(singleByteGif);
			out.flush();
			out.close();
			
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Filter out known red herring client errors by reducing their severity
	 * @param message the message thrown on the client
	 * @param userAgent the user agent string 
	 * @return the severity to treat the message with
	 */
	protected Level filterClientErrors(String message, String userAgent)
	{
		try
		{
			String result = message.substring(message.indexOf("clientError:") + 12, message.indexOf(":url:"));
			
			if (result != null)
			{
				if (ignoreFilters.contains(result))
				{
					return Level.CONFIG;
				}
				else if (warningFilters.contains(result))
				{
					return Level.WARNING;
				}
			}
		}
		catch (Exception e)
		{
			
		}
		
		if (userAgent != null && userAgent.contains("compatible; MSIE 8.0;"))
		{
			return Level.WARNING;
		}
		
		return Level.SEVERE;
	}
}
