/**
 * Copyright (c) 2006-2019, JGraph Ltd
 */
package io.slingcms.core.diagrams.online;

import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import javax.servlet.Servlet;

@Component(service = { Servlet.class }, property = { "sling.servlet.paths=/bin/diagrams/msgraphauth",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET })
@Designate(ocd = MSGraphAuthServletConfig.class, factory = true)
@SuppressWarnings("serial")
public class MSGraphAuthServlet extends AbsAuthServlet
{

	
	private static Config CONFIG = null;
    private static String SCRIPT_URL = "";

    @Activate
    public void activate(MSGraphAuthServletConfig config) {
        CONFIG = new Config();
        CONFIG.DEV_CLIENT_SECRET = config.msgraphDevClientSecret();
        CONFIG.CLIENT_SECRET = config.msgraphClientSecret();
        CONFIG.DEV_CLIENT_ID = config.msgraphDevClientId();
        CONFIG.CLIENT_ID = config.msgraphClientId();
        CONFIG.REDIRECT_URI = config.msgraphClientRedirectUri();
        CONFIG.DEV_REDIRECT_URI = config.msgraphClientDevRedirectUri();
        CONFIG.AUTH_SERVICE_URL = config.msgraphClientAuthServiceUrl();
        SCRIPT_URL = config.msgraphClientAuthServiceScriptUrl();

    }

	protected Config getConfig()
	{
//		if (CONFIG == null)
//		{
//			CONFIG = new Config();
//
//			try
//			{
//				CONFIG.DEV_CLIENT_SECRET = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(DEV_CLIENT_SECRET_FILE_PATH))
//						.replaceAll("\n", "");
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Dev client secret path invalid.");
//			}
//
//			try
//			{
//				CONFIG.CLIENT_SECRET = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(CLIENT_SECRET_FILE_PATH))
//						.replaceAll("\n", "");
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Client secret path invalid.");
//			}
//
//			try
//			{
//				CONFIG.DEV_CLIENT_ID = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(DEV_CLIENT_ID_FILE_PATH))
//						.replaceAll("\n", "");
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Dev client ID invalid.");
//			}
//
//			try
//			{
//				CONFIG.CLIENT_ID = Utils
//						.readInputStream(getServletContext()
//								.getResourceAsStream(CLIENT_ID_FILE_PATH))
//						.replaceAll("\n", "");
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("Client ID invalid.");
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
//				throw new RuntimeException("Redirect Uri is invalid");
//			}
//
//			CONFIG.DEV_REDIRECT_URI = "https://test.draw.io/microsoft";
//			CONFIG.AUTH_SERVICE_URL = "https://login.microsoftonline.com/common/oauth2/v2.0/token";
//		}
		
		return CONFIG;
	}	

	//TODO add this whole thing to config
	protected String processAuthResponse(String authRes, boolean jsonResponse)
	{
		StringBuffer res = new StringBuffer();
		
		//Call the opener callback function directly with the given json
		if (!jsonResponse)
		{
			res.append("<!DOCTYPE html><html><head><script src=\""+SCRIPT_URL+"\" type=\"text/javascript\"></script><script>");
			res.append("var authInfo = ");  //The following is a json containing access_token and refresh_token
		}
		
		res.append(authRes);

		if (!jsonResponse)
		{
			res.append(";");					
			res.append("if (window.opener != null && window.opener.onOneDriveCallback != null)"); 
			res.append("{");
			res.append("	window.opener.onOneDriveCallback(authInfo, window);");
			res.append("} else {");
			res.append("	Office.initialize = function () { Office.context.ui.messageParent(JSON.stringify(authInfo));}");
			res.append("}");
			res.append("</script></head><body></body></html>");
		}

		return res.toString();
	}
}
