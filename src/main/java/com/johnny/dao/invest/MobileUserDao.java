package com.johnny.dao.invest;

import com.johnny.entity.invest.MobileUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public interface MobileUserDao {

    List<MobileUser> findByPkey(@Param("pkey") String pkey);

    List<MobileUser> findByPkeys(List<String> pkeys);

    int updateName(MobileUser mobileUser);

    int updateNonField(MobileUser mobileUser);


}
