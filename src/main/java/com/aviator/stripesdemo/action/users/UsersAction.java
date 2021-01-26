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

package com.aviator.stripesdemo.action.users;

import com.aviator.stripesdemo.beans.users.UserBeanI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.stripes.action.*;

import javax.ejb.EJB;

@UrlBinding("/Users")
public class UsersAction {

    @EJB
    UserBeanI userBeanI;

    @DefaultHandler
    public Resolution getAllUsers() throws JsonProcessingException {
        ObjectMapper objetMapper=new ObjectMapper();
        return new StreamingResolution(
                objetMapper.writeValueAsString(userBeanI.getAllUser())
        );
    }
}
