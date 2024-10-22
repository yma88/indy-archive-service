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
package org.commonjava.indy.service.archive.jaxrs.mock;

import org.apache.commons.io.FileUtils;
import org.commonjava.indy.service.archive.controller.ArchiveController;
import org.commonjava.indy.service.archive.model.ArchiveStatus;
import org.commonjava.indy.service.archive.model.dto.HistoricalContentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.commonjava.indy.service.archive.util.TestUtil.getBytes;

@ApplicationScoped
@Alternative
public class MockArchiveController
                extends ArchiveController
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    public static final String NOT_FOUND_BUILD = "0000";

    public static final String EXIST_BUILD = "1111";

    public static final String EXIST_BUILD_ARCHIVE = "1111.zip";

    public static final String EXIST_LARGE_BUILD = "2222";

    public static final String EXIST_LARGE_BUILD_ARCHIVE = "2222.zip";

    public static final String ERR_BUILD = "3333";

    public static final String SUCCESS_BUILD = "5555";

    public static final int SIZE_50K = 1024 * 50; // 50K

    public static final int SIZE_20M = 1024 * 1024 * 20; // 20M

    @Override
    protected Boolean doGenerate( HistoricalContentDTO content )
    {
        String buildConfigId = content.getBuildConfigId();
        return buildConfigId.equals( SUCCESS_BUILD );
    }

    @Override
    public Optional<File> getArchiveInputStream( String buildConfigId ) throws IOException
    {
        switch ( buildConfigId )
        {
            case EXIST_BUILD:
                File file = new File( "data/archive", EXIST_BUILD_ARCHIVE );
                FileUtils.write( file, new String( getBytes( SIZE_50K ) ), "UTF-8" );
                return Optional.of( file );
            case EXIST_LARGE_BUILD:
                File large = new File( "data/archive", EXIST_LARGE_BUILD_ARCHIVE );
                FileUtils.write( large, new String( getBytes( SIZE_20M ) ), "UTF-8" );
                return Optional.of( large );
            case ERR_BUILD:
                logger.error( "ERR_BUILD mock should throw Exception." );
                throw new IOException();
            default:
                return Optional.empty();
        }
    }

    @Override
    public void deleteArchive( String buildConfigId ) throws IOException
    {
        if ( buildConfigId.equals( EXIST_BUILD ) )
        {
            super.deleteArchive( buildConfigId );
            return;
        }
        logger.error( "No existed build archive to be deleted for buildConfigId {}.", buildConfigId );
        throw new IOException();
    }

    @Override
    public boolean statusExists( final String buildConfigId )
    {
        return buildConfigId.equals( EXIST_BUILD );
    }

    @Override
    public String getStatus( String buildConfigId )
    {
        return buildConfigId.equals( EXIST_BUILD ) ? ArchiveStatus.completed.getArchiveStatus() : null;
    }
}
