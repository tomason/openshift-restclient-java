/*******************************************************************************
 * Copyright (c) 2015 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/package com.openshift.internal.restclient.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.openshift.restclient.model.route.ITLSConfig;

/**
 * Test for route functionality that should not depend on the
 * underlying api model
 * 
 * @author jeff.cantrill
 *
 */
public class RouteTest {
	
	private Route route;
	
	@Before
	public void setUp() throws Exception {
		route = spy(new Route(null, null, null));
		doReturn("www.host.com").when(route).getHost();
		doReturn("/abc").when(route).getPath();
	}

	@Test
	public void getURLShouldBeSecureWhenTLSConfigExists() {
		doReturn(mock(ITLSConfig.class)).when(route).getTLSConfig();
		assertEquals("https://www.host.com/abc", route.getURL());
	}

	@Test
	public void getURLShouldBeInSecureWhenTLSConfigDoesExists() {
		doReturn(null).when(route).getTLSConfig();
		assertEquals("http://www.host.com/abc", route.getURL());
	}

}
