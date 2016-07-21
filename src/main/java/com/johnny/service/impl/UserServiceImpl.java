package com.johnny.service.impl;

import com.johnny.entity.Person;
import com.johnny.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class UserServiceImpl implements UserService {

    private static final Map<String, Person> name2LastName = new HashMap();

    static {
        name2LastName.put("yt", new Person("yangtao", 10));
        name2LastName.put("yp", new Person("yangpeng", 15));
    }

    @Override
    public String name(UriInfo uriInfo, HttpServletRequest request, String name) {
        System.out.println("AbsolutePath:" + uriInfo.getAbsolutePath());
        System.out.println("BasePath:" + uriInfo.getBaseUri());
//        UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass());
//        URI rui1 = ub.build(name2LastName.get(name).getName());
        UriBuilder ub1 = uriInfo.getAbsolutePathBuilder().path(this.getClass().getInterfaces()[0], "aName");

        System.out.println("Header:aa  " + request.getHeader("aa"));
        System.out.println("Header:bb  " + request.getHeader("bb"));
        URI uri = ub1.build(name2LastName.get(name).getName());
        return uri.toString();
    }

    @Override
    public String aName(HttpServletRequest request, String name) {
        return null;
    }
}
