package com.johnny.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * Created by johnson.yang on 2016/5/10.
 */
@Path("/account")
public interface AccountService {

    @Path("/pkey")
    @GET
    String fetchUserId(@Context HttpServletRequest request, @QueryParam("pkey") String pkey);
}
