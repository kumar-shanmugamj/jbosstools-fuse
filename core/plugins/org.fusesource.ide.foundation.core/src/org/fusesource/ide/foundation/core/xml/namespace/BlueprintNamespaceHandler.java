/*******************************************************************************
 * Copyright (c) 2013 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.fusesource.ide.foundation.core.xml.namespace;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lhein
 */
public class BlueprintNamespaceHandler extends FindNamespaceHandlerSupport {
	
	private static String[] blueprintNamespaces = new String[]{ "http://camel.apache.org/schema/blueprint", 
			"http://www.osgi.org/xmlns/blueprint/v1.0.0", "https://www.osgi.org/xmlns/blueprint/v1.0.0" };
	public static Set<String> bpNamespaces = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(blueprintNamespaces)));
	
	public BlueprintNamespaceHandler() {
		super(bpNamespaces);
	}
}
