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

package com.aviator.stripesdemo.beans.users.impl;

import com.aviator.stripesdemo.beans.users.UserBeanI;
import com.aviator.stripesdemo.dao.users.UserDaoI;
import com.aviator.stripesdemo.model.MessageModel;
import com.aviator.stripesdemo.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserBeanImpl implements UserBeanI {

    /**
     *
     */
    @Inject
    public UserDaoI userDaoI;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    void postConstruct(){
       userDaoI.setEm(em);
    }

    @Override
    public MessageModel getAllUser() {
         return userDaoI.getUsers();
    }

    @Override
    public MessageModel createUser(User user) {
        return userDaoI.createUser(user);
    }
}
