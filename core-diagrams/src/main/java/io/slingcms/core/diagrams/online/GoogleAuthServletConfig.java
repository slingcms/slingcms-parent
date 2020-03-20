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

@ObjectClassDefinition(name = "%io.slingcms.core.diagrams.online.GoogleAuthServletConfig.name", description = "%io.slingcms.core.diagrams.online.GoogleAuthServletConfig.description", localization = "OSGI-INF/l10n/bundle")
public @interface GoogleAuthServletConfig {

    @AttributeDefinition(name = "%google_client_secret.name", description = "%google_client_secret.description")
    String googleClientSecret();

    @AttributeDefinition(name = "%google_client_id.name", description = "%google_client_id.description")
    String googleClientId();

    @AttributeDefinition(name = "%google_client_redirect_uri.name", description = "%google_client_redirect_uri.description")
    String googleClientRedirectUri();

    @AttributeDefinition(name = "%google_client_dev_redirect_uri.name", description = "%google_client_dev_redirect_uri.description")
    String googleClientDevRedirectUri()  default "https://test.draw.io/microsoft";

    @AttributeDefinition(name = "%google_client_auth_service_url.name", description = "%google_client_auth_service_url.description")
    String googleClientAuthServiceUrl() default "https://login.microsoftonline.com/common/oauth2/v2.0/token";

}
