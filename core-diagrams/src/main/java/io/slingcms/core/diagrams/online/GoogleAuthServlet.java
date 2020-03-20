/**
 * Copyright (c) 2006-2019, JGraph Ltd
 */
package io.slingcms.core.diagrams.online;

import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;

@Component(service = { Servlet.class }, property = { "sling.servlet.paths=/bin/diagrams/googleauth",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET })
@Designate(ocd = GoogleAuthServletConfig.class, factory = true)
@SuppressWarnings("serial")
public class GoogleAuthServlet extends AbsAuthServlet
{
	private static Config CONFIG = null;

    @Activate
    public void activate(GoogleAuthServletConfig config) {
        CONFIG = new Config();
        CONFIG.CLIENT_SECRET = config.googleClientSecret();
        CONFIG.DEV_CLIENT_SECRET = CONFIG.CLIENT_SECRET;
        CONFIG.CLIENT_ID = config.googleClientId();
        CONFIG.DEV_CLIENT_ID = config.googleClientId();
        CONFIG.REDIRECT_URI = config.googleClientRedirectUri();
        CONFIG.AUTH_SERVICE_URL = config.googleClientAuthServiceUrl();
        CONFIG.DEV_REDIRECT_URI = config.googleClientDevRedirectUri();
    }

	protected Config getConfig()
	{
//		if (CONFIG == null)
//		{
//			CONFIG = new Config();
//
//			try
//			{
//				CONFIG.CLIENT_SECRET = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(CLIENT_SECRET_FILE_PATH))
//						.replaceAll("\n", "");
//				CONFIG.DEV_CLIENT_SECRET = CONFIG.CLIENT_SECRET;
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Client secret path invalid");
//			}
//
//			try
//			{
//				CONFIG.CLIENT_ID = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(CLIENT_ID_FILE_PATH))
//						.replaceAll("\n", "");
//				CONFIG.DEV_CLIENT_ID = CONFIG.CLIENT_ID;
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Client ID path invalid");
//			}
//
//			try
//			{
//				CONFIG.REDIRECT_URI = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(CLIENT_REDIRECT_URI_FILE_PATH))
//						.replaceAll("\n", "");
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Client ID path invalid");
//			}
//
//			CONFIG.AUTH_SERVICE_URL = "https://www.googleapis.com/oauth2/v4/token";
//			CONFIG.DEV_REDIRECT_URI = "https://test.draw.io/google";
//		}
//
		return CONFIG;
	}
    //TODO add this whole thing to config
	protected String processAuthResponse(String authRes, boolean jsonResponse)
	{
		StringBuffer res = new StringBuffer();
		
		//In Office Add-in, we don't have access to opened window to attach a function to it, 
		//	also with the redirect (since we had to open google auth in the same window) we lost Office Messaging.
		//	This is due to using Google own file picker instead of creating our own picker 
		//	(as we did with OneDrive since its picker only support popup windows which is not supported in Office)
		//	This is why we load drive.js which define onGDriveCallback and redirects automatically to the page including the picker
		//	For other scenarios, we use another function name (onGoogleDriveCallback)
		if (!jsonResponse)
		{
			res.append("<!DOCTYPE html><html><head>");
			res.append("<script src=\"/connect/office365/js/drive.js\" type=\"text/javascript\"></script>");
			res.append("<script type=\"text/javascript\">");
			res.append("var authInfo = ");  //The following is a json containing access_token and redresh_token
		}
		
		res.append(authRes);
		
		if (!jsonResponse)
		{
			res.append(";");
			res.append("if (window.opener != null && window.opener.onGoogleDriveCallback != null)"); 
			res.append("{");
			res.append("	window.opener.onGoogleDriveCallback(authInfo, window);");
			res.append("} else {");
			res.append("	onGDriveCallback(authInfo);");
			res.append("}");
			res.append("</script>");
			res.append("</head><body></body></html>");
		}
		
		return res.toString();
	}
}
