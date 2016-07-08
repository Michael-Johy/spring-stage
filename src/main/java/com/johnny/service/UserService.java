package com.johnny.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by johnson.yang on 2016/5/11.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public interface UserService {

    @GET
    @Path("/name")
    String name(@Context UriInfo uriInfo, @Context HttpServletRequest request, @QueryParam("name") String name);

    @GET
    @Path("/name/{name}")
    String aName(@Context HttpServletRequest request, @PathParam("name") String name);

}
