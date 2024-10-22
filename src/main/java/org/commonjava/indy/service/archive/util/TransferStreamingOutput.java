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
package org.commonjava.indy.service.archive.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransferStreamingOutput
        implements StreamingOutput
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    private final InputStream stream;

    public TransferStreamingOutput( InputStream stream )
    {
        this.stream = stream;
    }

    @Override
    public void write( OutputStream out ) throws IOException, WebApplicationException
    {
        try ( CountingOutputStream cout = new CountingOutputStream( out ) )
        {
            IOUtils.copy( stream, cout );
            logger.trace( "Wrote: {} bytes", cout.getByteCount() );
        }
        finally
        {
            IOUtils.closeQuietly( stream, null );
        }
    }
}
