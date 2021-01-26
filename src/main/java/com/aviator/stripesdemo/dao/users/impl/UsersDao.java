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

package com.aviator.stripesdemo.dao.users.impl;

import com.aviator.stripesdemo.dao.generic.GenericDao;
import com.aviator.stripesdemo.dao.users.UserDaoI;
import com.aviator.stripesdemo.model.MessageModel;
import com.aviator.stripesdemo.model.User;

import javax.persistence.EntityManager;

public class UsersDao extends GenericDao<User, Long> implements UserDaoI {

    private EntityManager em;

    @Override
    public MessageModel getUsers() {
        return new MessageModel(
                true,
                "Done",
                findAll()
        );
    }

    @Override
    public MessageModel createUser(User user) {
        User u = create(user);
        return new MessageModel(
                u != null,
                u == null ? "Failed, please try again" : "Operation success",
                u
        );
    }
}
