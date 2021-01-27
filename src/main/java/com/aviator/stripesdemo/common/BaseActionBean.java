/*
 *
 *  * Copyright (c) 2021.  Aviator
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package com.aviator.stripesdemo.common;

import com.aviator.stripesdemo.beans.users.UserBeanI;
import com.aviator.stripesdemo.handlers.BaseActionBeanContextSP;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import javax.ejb.EJB;

public abstract class BaseActionBean implements ActionBean {
    @EJB
    protected UserBeanI userBeanI;

    public BaseActionBean() {
    }

    private BaseActionBeanContextSP beanContextSP;

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.beanContextSP = (BaseActionBeanContextSP) actionBeanContext;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.beanContextSP;
    }
}
