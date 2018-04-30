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
package fr.paris.lutece.plugins.resource.modules.extendableresource.service.provider;

import fr.paris.lutece.plugins.extend.business.type.ExtendableResourceType;
import fr.paris.lutece.plugins.extend.service.ExtendableResourceManager;
import fr.paris.lutece.plugins.extend.service.type.ExtendableResourceTypeService;
import fr.paris.lutece.plugins.resource.business.IResource;
import fr.paris.lutece.plugins.resource.business.IResourceType;
import fr.paris.lutece.plugins.resource.business.ResourceTypeDefaultImplementation;
import fr.paris.lutece.plugins.resource.modules.extendableresource.business.ResourceExtendableResource;
import fr.paris.lutece.plugins.resource.service.ResourceCacheService;
import fr.paris.lutece.plugins.resource.service.provider.IResourceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Resource provider for extendable resources
 */
public class ExtendableResourceResourceProvider implements IResourceProvider
{
    @Inject
    @Named( value = ExtendableResourceManager.BEAN_MANAGER )
    private ExtendableResourceManager _extendableResourceManager;
    @Inject
    @Named( value = ExtendableResourceTypeService.BEAN_SERVICE )
    private ExtendableResourceTypeService _extendableResourceTypeService;

    /**
     * Default constructor
     */
    public ExtendableResourceResourceProvider( )
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IResourceType> getResourceTypeList( )
    {
        List<ExtendableResourceType> listExtendableResourceType = _extendableResourceTypeService.findAll( Locale
                .getDefault( ) );

        List<IResourceType> listResourceType = new ArrayList<IResourceType>( listExtendableResourceType.size( ) );
        for ( ExtendableResourceType resourceType : listExtendableResourceType )
        {
            listResourceType.add( new ResourceTypeDefaultImplementation( resourceType.getKey( ), resourceType
                    .getDescription( ) ) );
        }
        return listResourceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isResourceTypeManaged( String strResourceTypeName )
    {
        return _extendableResourceManager.getExtendableResourceService( strResourceTypeName ) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResource getResource( String strIdResource, String strResourceTypeName )
    {
        String strCacheKey = ResourceCacheService.getResourceCacheKey( strIdResource, strResourceTypeName );
        ResourceExtendableResource resource = (ResourceExtendableResource) ResourceCacheService.getInstance( )
                .getFromCache( strCacheKey );
        if ( resource == null )
        {
            resource = new ResourceExtendableResource( _extendableResourceManager.getResource( strIdResource,
                    strResourceTypeName ) );
            ResourceCacheService.getInstance( ).putInCache( strCacheKey, resource );
        }
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IResource> getListResources( String strResourceTypeName )
    {
        // it is not possible to get the list of resources of a given type for extendable resources
        return new ArrayList<IResource>( 0 );
    }
}
