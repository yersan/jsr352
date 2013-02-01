/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
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
 
package org.mybatch.metadata;

import org.mybatch.job.Listeners;
import org.mybatch.job.Properties;
import org.mybatch.job.Step;

public class StepMerger {
    private Step parent;
    private Step child;

    public StepMerger(Step parent, Step child) {
        this.parent = parent;
        this.child = child;
    }

    public void merge() {
        //merge step attributes

        merge(parent.getProperties(), child.getProperties());
        merge(parent.getListeners(), child.getListeners());
    }

    private void merge(Properties parentProps, Properties childProps) {
        if (parentProps == null) {
            return;
        }
        if (childProps == null) {
            child.setProperties(parentProps);
            return;
        }
        JobMerger.mergeProperties(parentProps, childProps);
    }

    private void merge(Listeners parentListeners, Listeners childListeners) {
        if (parentListeners == null) {
            return;
        }
        if (childListeners == null) {
            child.setListeners(parentListeners);
            return;
        }
        JobMerger.mergeListeners(parentListeners, childListeners);
    }

}