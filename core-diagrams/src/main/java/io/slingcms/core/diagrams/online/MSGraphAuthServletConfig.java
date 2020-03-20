/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.slingcms.core.diagrams.online;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "%io.slingcms.core.diagrams.online.MSGraphAuthServlet.name", description = "%io.slingcms.core.diagrams.online.MSGraphAuthServlet.description", localization = "OSGI-INF/l10n/bundle")
public @interface MSGraphAuthServletConfig {

    public static String DEV_CLIENT_SECRET_FILE_PATH = "/WEB-INF/msgraph_dev_client_secret";
    public static String CLIENT_SECRET_FILE_PATH = "/WEB-INF/msgraph_client_secret";
    public static String DEV_CLIENT_ID_FILE_PATH = "/WEB-INF/msgraph_dev_client_id";
    public static String CLIENT_ID_FILE_PATH = "/WEB-INF/msgraph_client_id";
    public static String CLIENT_REDIRECT_URI_FILE_PATH = "/WEB-INF/msgraph_client_redirect_uri";


    @AttributeDefinition(name = "%msgraph_dev_client_secret.name", description = "%msgraph_dev_client_secret.description")
    String msgraphDevClientSecret();

    @AttributeDefinition(name = "%msgraph_client_secret.name", description = "%msgraph_client_secret.description")
    String msgraphClientSecret();

    @AttributeDefinition(name = "%msgraph_dev_client_id.name", description = "%msgraph_dev_client_id.description")
    String msgraphDevClientId();

    @AttributeDefinition(name = "%msgraph_client_id.name", description = "%msgraph_client_id.description")
    String msgraphClientId();

    @AttributeDefinition(name = "%msgraph_client_redirect_uri.name", description = "%msgraph_client_redirect_uri.description")
    String msgraphClientRedirectUri();

    @AttributeDefinition(name = "%msgraph_client_dev_redirect_uri.name", description = "%msgraph_client_dev_redirect_uri.description")
    String msgraphClientDevRedirectUri() default "https://test.draw.io/microsoft";

    @AttributeDefinition(name = "%msgraph_client_auth_service_url.name", description = "%msgraph_client_auth_service_url.description")
    String msgraphClientAuthServiceUrl() default "https://login.microsoftonline.com/common/oauth2/v2.0/token";

    @AttributeDefinition(name = "%msgraph_client_auth_service_script_url.name", description = "%msgraph_client_auth_service_script_url.description")
    String msgraphClientAuthServiceScriptUrl() default "https://appsforoffice.microsoft.com/lib/1.1/hosted/office.js";



}
