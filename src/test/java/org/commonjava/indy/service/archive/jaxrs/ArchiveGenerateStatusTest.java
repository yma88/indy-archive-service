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

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.commonjava.indy.service.archive.jaxrs.mock.MockTestProfile;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.commonjava.indy.service.archive.jaxrs.mock.MockArchiveController.EXIST_BUILD;
import static org.commonjava.indy.service.archive.jaxrs.mock.MockArchiveController.NOT_FOUND_BUILD;

@QuarkusTest
@TestProfile( MockTestProfile.class )
public class ArchiveGenerateStatusTest
{
    @Test
    public void testArchiveStatusExists()
    {
        given().when()
               .get( "/api/archive/status/" + EXIST_BUILD )
               .then()
               .statusCode( OK.getStatusCode() )
               .contentType( MediaType.TEXT_PLAIN );
    }

    @Test
    public void testArchiveStatusNotExists()
    {
        given().when()
               .get( "/api/archive/status/" + NOT_FOUND_BUILD )
               .then()
               .statusCode( NOT_FOUND.getStatusCode() )
               .contentType( MediaType.TEXT_PLAIN );
    }
}