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
import com.aviator.stripesdemo.model.MessageModel;
import com.aviator.stripesdemo.model.Pojo;
import com.aviator.stripesdemo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@UrlBinding("/Users")
@Path("/Users")
public class UsersAction implements ActionBean {

    @EJB
    UserBeanI userBeanI;

    //    @DefaultHandler
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String getAllUsers() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("count", 1234);
        map.put("value", 789800);
        map.put("County", "Taita Tavete");
        if (userBeanI != null) {
            map.put("people", userBeanI.getAllUser());
        } else {
            map.put("EJB", "Not initialized");
        }

        ArrayList<Pojo> pojos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                integers.add(j);
            }
            pojos.add(new Pojo("Pojo " + i, integers));
        }
        map.put("Pojo", pojos);

//        return new StreamingResolution(MediaType.APPLICATION_JSON,
//                objetMapper.writeValueAsString(
//                        new MessageModel(
//                                true,
//                                "Done",
//                                map
//                        )
//                )
//        );

        return toJson(
                new MessageModel(
                        true,
                        "Done",
                        map
                )
        );
    }


    @POST
    @Consumes(value = {MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String createUser(User user) throws JsonProcessingException {
        return toJson(userBeanI.createUser(user));
    }


    private String toJson(Object data) throws JsonProcessingException {
        return new ObjectMapper()
                .writeValueAsString(data);
    }

    ActionBeanContext actionBeanContext;

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.actionBeanContext = actionBeanContext;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.actionBeanContext;
    }
}
