/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.fusesource.ide.camel.editor.component.wizard;

import java.util.ArrayList;

import org.fusesource.ide.camel.editor.component.wizard.ComponentManager;
import org.fusesource.ide.camel.model.service.core.catalog.components.Component;
import org.fusesource.ide.camel.model.service.core.catalog.components.ComponentModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ComponentManagerTest {
	@Mock
	private ComponentModel componentModel;
	private ComponentManager componentManager;
	private Component componentWithoutTag;
	private Component componentWithTag1;
	private Component componentWithTag22;
	private Component componentWithTag2;
	private Component componentWithSeveralTags;

	@Before
	public void setup() {
		final ArrayList<Component> supportedComponents = new ArrayList<>();
		setupComponentWithoutTag(supportedComponents);
		setupComponentWithOneTag(supportedComponents);
		setupComponentsWithSharedTags(supportedComponents);
		setupComponentWithSeveralTags(supportedComponents);

		doReturn(supportedComponents).when(componentModel).getSupportedComponents();
		componentManager = new ComponentManager(componentModel);
	}

	/**
	 * @param supportedComponents
	 */
	private void setupComponentWithoutTag(final ArrayList<Component> supportedComponents) {
		componentWithoutTag = new Component();
		supportedComponents.add(componentWithoutTag);
	}

	/**
	 * @param supportedComponents
	 */
	private void setupComponentWithOneTag(final ArrayList<Component> supportedComponents) {
		componentWithTag1 = new Component();
		final ArrayList<String> listSingleTag1 = new ArrayList<>();
		listSingleTag1.add("tag1");
		componentWithTag1.setTags(listSingleTag1);
		supportedComponents.add(componentWithTag1);
	}

	/**
	 * @param supportedComponents
	 */
	private void setupComponentsWithSharedTags(final ArrayList<Component> supportedComponents) {
		componentWithTag2 = new Component();
		final ArrayList<String> listSingleTag2 = new ArrayList<>();
		listSingleTag2.add("tag2");
		componentWithTag2.setTags(listSingleTag2);
		supportedComponents.add(componentWithTag2);

		componentWithTag22 = new Component();
		componentWithTag22.setTags(listSingleTag2);
		supportedComponents.add(componentWithTag22);
	}

	/**
	 * @param supportedComponents
	 */
	private void setupComponentWithSeveralTags(final ArrayList<Component> supportedComponents) {
		componentWithSeveralTags = new Component();
		final ArrayList<String> listSeveralTags = new ArrayList<>();
		listSeveralTags.add("tagSeveral1");
		listSeveralTags.add("tagSeveral2");
		componentWithSeveralTags.setTags(listSeveralTags);
		supportedComponents.add(componentWithSeveralTags);
	}

	@Test
	public void testGetComponentForSingleTag() throws Exception {
		assertThat(componentManager.getComponentForTag("tag1")).containsOnly(componentWithTag1);
	}

	@Test
	public void testGetComponentForTagShared() throws Exception {
		assertThat(componentManager.getComponentForTag("tag2")).containsOnly(componentWithTag2, componentWithTag22);
	}

	@Test
	public void testGetComponentForTagMultiple() throws Exception {
		assertThat(componentManager.getComponentForTag("tagSeveral1")).containsOnly(componentWithSeveralTags);
		assertThat(componentManager.getComponentForTag("tagSeveral2")).containsOnly(componentWithSeveralTags);
	}

	@Test
	public void testGetComponentWithoutTag() throws Exception {
		assertThat(componentManager.getComponentWithoutTag()).containsOnly(componentWithoutTag);
	}

	@Test
	public void testGetTagWithoutComponent_returnEmpty() throws Exception {
		assertThat(componentManager.getComponentForTag("doest exist")).isEmpty();
	}

	@Test
	public void testGetTags() throws Exception {
		assertThat(componentManager.getTags()).containsOnly("tag1", "tag2", "tagSeveral1", "tagSeveral2");
	}

	@Test
	public void testgetAllComponents(){
		assertThat(componentManager.getAllComponents()).containsOnly(componentWithoutTag, componentWithSeveralTags, componentWithTag1, componentWithTag2, componentWithTag22);
	}


}
