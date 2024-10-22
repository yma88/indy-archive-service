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
package org.commonjava.indy.service.archive.ftests;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.commonjava.indy.service.archive.ftests.profile.ArchiveFunctionProfile;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.MediaType;
import java.io.File;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * <b>GIVEN:</b>
 * <ul>
 *     <li>Request to generate an archive</li>
 * </ul>
 *
 * <br/>
 * <b>WHEN:</b>
 * <ul>
 *     <li>Request api for this archive generating status</li>
 * </ul>
 *
 * <br/>
 * <b>THEN:</b>
 * <ul>
 *     <li>The status can response correctly</li>
 * </ul>
 */
@QuarkusTest
@TestProfile( ArchiveFunctionProfile.class )
@Tag( "function" )
public class ArchiveGenerateStatusContentTest
                extends AbstractArchiveFuncTest
{
    @Test
    public void testRetrieveContent()
    {
        File file = new File( "data/archive", SUCCESS_BUILD_ARCHIVE );
        assertFalse( file.exists() );
        assertThat( file.length(), equalTo( 0L ) );

        given().when()
               .body( SUCCESS_TRACKED.getBytes() )
               .contentType( MediaType.APPLICATION_JSON )
               .post( "/api/archive/generate" )
               .then()
               .statusCode( anyOf( is( 202 ), is( 200 ) ) );

        given().when()
               .get( "/api/archive/status/" + SUCCESS_BUILD )
               .then()
               .statusCode( OK.getStatusCode() )
               .contentType( MediaType.TEXT_PLAIN );
    }
}
