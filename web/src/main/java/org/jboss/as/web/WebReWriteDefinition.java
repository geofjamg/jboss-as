/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.web;

import org.jboss.as.controller.ReloadRequiredRemoveStepHandler;
import org.jboss.as.controller.ReloadRequiredWriteAttributeHandler;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinitionBuilder;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.operations.validation.StringLengthValidator;
import org.jboss.as.controller.registry.AttributeAccess;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.dmr.ModelType;

/**
 * @author Tomaz Cerar
 * @created 23.2.12 17:13
 */
public class WebReWriteDefinition extends SimpleResourceDefinition {
    public static final WebReWriteDefinition INSTANCE = new WebReWriteDefinition();

    protected static final SimpleAttributeDefinition PATTERN =
            new SimpleAttributeDefinitionBuilder(Constants.PATTERN, ModelType.STRING, false)
                    .setXmlName(Constants.PATTERN)
                    .setFlags(AttributeAccess.Flag.RESTART_ALL_SERVICES)
                    .setValidator(new StringLengthValidator(1, false))
                    .build();


    protected static final SimpleAttributeDefinition SUBSTITUTION =
            new SimpleAttributeDefinitionBuilder(Constants.SUBSTITUTION, ModelType.STRING, false)
                    .setXmlName(Constants.SUBSTITUTION)
                    .setFlags(AttributeAccess.Flag.RESTART_ALL_SERVICES)
                    .setValidator(new StringLengthValidator(1, false))
                    .build();
    protected static final SimpleAttributeDefinition FLAGS =
            new SimpleAttributeDefinitionBuilder(Constants.FLAGS, ModelType.STRING, false)
                    .setXmlName(Constants.FLAGS)
                    .setFlags(AttributeAccess.Flag.RESTART_ALL_SERVICES)
                    .setValidator(new StringLengthValidator(1, false))
                    .build();

    protected static final SimpleAttributeDefinition[] ATTRIBUTES = {PATTERN, SUBSTITUTION, FLAGS};

    private WebReWriteDefinition() {
        super(WebExtension.REWRITE_PATH,
                WebExtension.getResourceDescriptionResolver("virtual-server.rewrite"),
                WebReWriteAdd.INSTANCE,
                new ReloadRequiredRemoveStepHandler());
    }

    @Override
    public void registerAttributes(ManagementResourceRegistration rewrite) {
        rewrite.registerReadWriteAttribute(PATTERN, null, new ReloadRequiredWriteAttributeHandler(PATTERN));
        rewrite.registerReadWriteAttribute(SUBSTITUTION, null, new ReloadRequiredWriteAttributeHandler(SUBSTITUTION));
        rewrite.registerReadWriteAttribute(FLAGS, null, new ReloadRequiredWriteAttributeHandler(FLAGS));
    }
}
