/*
 * Copyright (c) 2002-2026, Mairie de Paris
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.resource.business.IResource;
import fr.paris.lutece.plugins.resource.service.ResourceService;
import fr.paris.lutece.plugins.resource.service.provider.IResourceProvider;
import fr.paris.lutece.portal.business.page.Page;
import fr.paris.lutece.portal.business.page.PageHome;
import fr.paris.lutece.test.LuteceTestCase;
import jakarta.inject.Inject;

/**
 * Extendable resources served to plugin-resource.
 */
public class ExtendableResourceResourceProviderTest extends LuteceTestCase
{
    private static final String ID_ROOT_PAGE = "1";

    @Inject
    private ResourceService _resourceService;

    /**
     * plugin-resource finds this provider for a type plugin-extend can extend, and reads the resource through it.
     */
    @Test
    public void testPageIsServedAsResource( )
    {
        IResourceProvider provider = _resourceService.getResourceProvider( Page.RESOURCE_TYPE );

        assertTrue( provider instanceof ExtendableResourceResourceProvider, "provider of the page type" );

        IResource resource = _resourceService.getResource( ID_ROOT_PAGE, Page.RESOURCE_TYPE );

        assertNotNull( resource, "root page resource" );
        assertEquals( ID_ROOT_PAGE, resource.getIdResource( ), "id" );
        assertEquals( Page.RESOURCE_TYPE, resource.getResourceType( ), "type" );
        assertEquals( PageHome.getPage( 1 ).getExtendableResourceName( ), resource.getResourceName( ), "name" );
    }

    /**
     * A type plugin-extend cannot extend is left to the other providers.
     */
    @Test
    public void testUnknownTypeIsNotManaged( )
    {
        assertFalse( _resourceService.isResourceTypeManaged( "unit_test_unknown_type" ), "unknown type" );
    }

    /**
     * An id plugin-extend resolves to no resource gives no resource, not an exception.
     */
    @Test
    public void testUnresolvedIdGivesNoResource( )
    {
        assertNull( _resourceService.getResource( "not_a_page_id", Page.RESOURCE_TYPE ), "non numeric page id" );
    }
}
