/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.common.model.handlers;

import java.util.Properties;
import org.jboss.tools.common.meta.action.*;
import org.jboss.tools.common.meta.action.impl.AbstractHandler;
import org.jboss.tools.common.model.XModelException;
import org.jboss.tools.common.model.XModelObject;

public class OpenEditorHandler extends AbstractHandler {
	static SpecialWizard wizard = SpecialWizardFactory.createSpecialWizard("org.jboss.tools.common.editor.OpenEditorWizard"); //$NON-NLS-1$
	
	public boolean isEnabled(XModelObject object) {
		return (object != null && object.isActive());
	}

	public void executeHandler(XModelObject object, Properties p) throws XModelException {
		if(wizard == null) return;
		if(p == null) p = new Properties();
		p.put("object", object); //$NON-NLS-1$
		wizard.setObject(p);
		int i = wizard.execute();
		if(i != 0 && p.get("exception") != null) { //$NON-NLS-1$
			Exception e = (Exception)p.get("exception"); //$NON-NLS-1$
			if(e instanceof XModelException) throw (XModelException)e;
			throw new XModelException(e); 
		}
	}
	
}
