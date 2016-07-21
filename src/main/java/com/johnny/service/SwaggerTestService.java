package com.johnny.service;

import com.johnny.entity.myswagger.Swagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by johnny01.yang on 2016/7/21.
 */
@Path("/mySwagger")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/mySwagger", description = "Operation about mySwagger")
public interface SwaggerTestService {

    @GET
    @Path("/{name}")
    @ApiOperation(value = "get swagger by name")
    String getSwagger(@ApiParam(value = "name", required = true) @PathParam("name") String username);

    @POST
    @ApiOperation(value = "createMySwagger", notes = "This can only be done by the logged in mySwagger")
    String createSwagger(@ApiParam(value = "created swagger object", required = true) Swagger swagger);

    @GET
    @Path("/age/{age}")
    @ApiOperation(value = "age swagger by name")
    String getByAge(@ApiParam(value = "getByAge", required = true) @PathParam("age") Integer age);
}
