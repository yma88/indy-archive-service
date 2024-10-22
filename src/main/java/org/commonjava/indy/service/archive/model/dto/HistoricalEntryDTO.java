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

import org.commonjava.indy.service.archive.model.StoreKey;

public class HistoricalEntryDTO
        implements Comparable<HistoricalEntryDTO>
{

//    @ApiModelProperty( value = "The Indy key for the repository/group this where content was stored.",
//            allowableValues = "remote:<name>, hosted:<name>, group:<name>" )
    private StoreKey storeKey;

    private String path;

    private String md5;

    private String sha256;

    private String sha1;

    private Long size;

    //for shared-imports, this would be empty/null, for the ones don't promote into shared-imports, this would probably be given.
    private String originUrl;

    private String localUrl;

    public HistoricalEntryDTO()
    {
    }

    public HistoricalEntryDTO( final StoreKey storeKey, final String path )
    {
        this.storeKey = storeKey;
        this.path = path.startsWith( "/" ) ? path : "/" + path;
    }

    public StoreKey getStoreKey()
    {
        return storeKey;
    }

    public void setStoreKey( final StoreKey storeKey )
    {
        this.storeKey = storeKey;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( final String path )
    {
        this.path = path.startsWith( "/" ) ? path : "/" + path;
    }

    public String getMd5()
    {
        return md5;
    }

    public void setMd5( final String md5 )
    {
        this.md5 = md5;
    }

    public String getSha256()
    {
        return sha256;
    }

    public void setSha256( final String sha256 )
    {
        this.sha256 = sha256;
    }

    public String getSha1()
    {
        return sha1;
    }

    public void setSha1( final String sha1 )
    {
        this.sha1 = sha1;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( final Long size )
    {
        this.size = size;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl( String originUrl ) {
        this.originUrl = originUrl;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl( String localUrl ) {
        this.localUrl = localUrl;
    }

    @Override
    public int compareTo( final HistoricalEntryDTO other )
    {
        int comp = storeKey.compareTo( other.getStoreKey() );
        if ( comp == 0 )
        {
            comp = path.compareTo( other.getPath() );
        }

        return comp;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( path == null ) ? 0 : path.hashCode() );
        result = prime * result + ( ( storeKey == null ) ? 0 : storeKey.hashCode() );
        return result;
    }

    @Override
    public boolean equals( final Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final HistoricalEntryDTO other = ( HistoricalEntryDTO ) obj;
        if ( path == null )
        {
            if ( other.path != null )
            {
                return false;
            }
        }
        else if ( !path.equals( other.path ) )
        {
            return false;
        }
        if ( storeKey == null )
        {
            if ( other.storeKey != null )
            {
                return false;
            }
        }
        else if ( !storeKey.equals( other.storeKey ) )
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return String.format( "HistoricalEntryDTO [\n  storeKey=%s\n  path=%s\n  originUrl=%s\n  localUrl=%s\n  size=%d\n  md5=%s\n  sha256=%s\n  sha1=%s\n]",
                storeKey, path, originUrl, localUrl, size, md5, sha256, sha1 );
    }

    public String getStorePath()
    {
        return String.format( "/%s/%s/%s", storeKey.getPackageType(), storeKey.getType(), storeKey.getName() );
    }
}
