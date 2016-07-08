package com.johnny.service.impl;

import com.johnny.common.AccessControl;
import com.johnny.service.AccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by johnson.yang on 2016/5/10.
 */
public class AccountServiceImpl implements AccountService {

    @Override
    @AccessControl(verifyToken = true, iName = "userid")
    public String fetchUserId(HttpServletRequest request, String pkey) {
        if (pkey.equals("yt"))
            return "johnson";
        return "jack";
    }
}
