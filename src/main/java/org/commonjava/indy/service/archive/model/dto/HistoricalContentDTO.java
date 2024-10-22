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
package org.commonjava.indy.service.archive.model.dto;

public class HistoricalContentDTO
{
    private String buildConfigId;

    private HistoricalEntryDTO[] downloads;

    public HistoricalContentDTO()
    {
    }

    public HistoricalContentDTO( String buildConfigId, HistoricalEntryDTO[] downloads ) {
        this.buildConfigId = buildConfigId;
        this.downloads = downloads;
    }

    public String getBuildConfigId() {
        return buildConfigId;
    }

    public void setBuildConfigId( String buildConfigId ) {
        this.buildConfigId = buildConfigId;
    }

    public HistoricalEntryDTO[] getDownloads()
    {
        return downloads;
    }

    public void setDownloads( final HistoricalEntryDTO[] downloads )
    {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String content = String.format( "HistoricalContentDTO [\n  buildConfigId=%s\n]\n", buildConfigId );
        builder.append( content );
        for ( HistoricalEntryDTO entry : downloads )
        {
            builder.append( entry.toString() );
            builder.append( "\n" );
        }
        return builder.toString();
    }
}
