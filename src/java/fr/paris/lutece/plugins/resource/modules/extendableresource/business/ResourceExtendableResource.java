/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.resource.modules.extendableresource.business;

import fr.paris.lutece.plugins.resource.business.IResource;
import fr.paris.lutece.portal.service.resource.IExtendableResource;


/**
 * Class that implement both IResource and IExtendableResource interfaces
 */
public class ResourceExtendableResource implements IResource, IExtendableResource
{
    private static final long serialVersionUID = 5868569634572985889L;

    private String _strIdExtendableResource;
    private String _strExtendableResourceType;
    private String _strName;
    private String _strDescription;
    private String _strImagesUrl;

    /**
     * Creates a new ResourceExtentableResource from an extendable resource
     * @param extendableResource the extendable resource to read data from
     */
    public ResourceExtendableResource( IExtendableResource extendableResource )
    {
        this._strIdExtendableResource = extendableResource.getIdExtendableResource( );
        this._strExtendableResourceType = extendableResource.getExtendableResourceType( );
        this._strName = extendableResource.getExtendableResourceName( );
        this._strDescription = extendableResource.getExtendableResourceDescription( );
        this._strImagesUrl = extendableResource.getExtendableResourceImageUrl( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdExtendableResource( )
    {
        return _strIdExtendableResource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtendableResourceType( )
    {
        return _strExtendableResourceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtendableResourceName( )
    {
        return _strName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtendableResourceDescription( )
    {
        return _strDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtendableResourceImageUrl( )
    {
        return _strImagesUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdResource( )
    {
        return getIdExtendableResource( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResourceType( )
    {
        return getExtendableResourceType( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResourceName( )
    {
        return getExtendableResourceName( );
    }

}
