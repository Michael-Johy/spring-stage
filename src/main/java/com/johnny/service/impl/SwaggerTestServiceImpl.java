package com.johnny.service.impl;

import com.johnny.dao.SwaggerTestDao;
import com.johnny.entity.myswagger.Swagger;
import com.johnny.service.SwaggerTestService;

/**
 * Created by johnny01.yang on 2016/7/21.
 */
public class SwaggerTestServiceImpl extends BaseServiceImpl implements SwaggerTestService {

    private SwaggerTestDao swaggerTestDao;

    @Override
    public String getSwagger(String username) {
        Swagger swagger = swaggerTestDao.getByName(username);
        return successJson(swagger);
    }

    @Override
    public String createSwagger(Swagger swagger) {
        boolean created = swaggerTestDao.createSwagger(swagger);
        return successJson(created);
    }

    @Override
    public String getByAge(Integer age) {
        Swagger swagger = swaggerTestDao.getByAge(age);
        return successJson(swagger);
    }

    public void setSwaggerTestDao(SwaggerTestDao swaggerTestDao) {
        this.swaggerTestDao = swaggerTestDao;
    }
}
