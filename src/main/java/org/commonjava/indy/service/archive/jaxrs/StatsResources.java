/**
 * Copyright (C) 2021-2023 Red Hat, Inc. (https://github.com/Commonjava/service-parent)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.indy.service.archive.jaxrs;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.commonjava.indy.service.archive.controller.StatsController;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Tag( name = "Generic Infrastructure Queries (UI Support)",
                description = "Read-only operations for retrieving information about this system" )
@Path( "/api/stats" )
public class StatsResources
{
    @Inject
    StatsController statsController;

    @Path( "version-info" )
    @GET
    @Produces( APPLICATION_JSON )
    public Uni<JsonObject> getAppVersion()
    {
        return statsController.getStatsInfo();
    }
}
