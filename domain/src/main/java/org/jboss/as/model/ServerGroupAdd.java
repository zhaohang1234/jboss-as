/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
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

package org.jboss.as.model;

import org.jboss.as.model.socket.SocketBindingGroupRefElement;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class ServerGroupAdd extends AbstractDomainModelUpdate<Void> {
    private static final long serialVersionUID = 8526537198264820276L;

    private final String name;
    private final String profile;
    private final JvmElement jvm;
    private final SocketBindingGroupRefElement ref;

    public ServerGroupAdd(final String name, final String profile, final JvmElement jvm, SocketBindingGroupRefElement bindingGroup) {
        this.name = name;
        this.profile = profile;
        this.jvm = jvm;
        this.ref = bindingGroup;
    }

    /** {@inheritDoc} */
    protected void applyUpdate(DomainModel element) throws UpdateFailedException {
        final ServerGroupElement group = element.addServerGroup(name, profile);
        if(group == null) {
            throw new UpdateFailedException("duplciate server group " + name);
        }
        group.setJvm(jvm);
        group.setSocketBindingGroupRefElement(ref);
    }

    /** {@inheritDoc} */
    public AbstractDomainModelUpdate<?> getCompensatingUpdate(DomainModel original) {
        return new ServerGroupRemove(name);
    }

    /** {@inheritDoc} */
    protected AbstractServerModelUpdate<Void> getServerModelUpdate() {
        // TODO Auto-generated method stub
        return null;
    }
}
